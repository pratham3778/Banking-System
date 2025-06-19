package com.prathamproj.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.prathamproj.dto.EmailDetails;
import com.prathamproj.entity.Transaction;
import com.prathamproj.entity.User;
import com.prathamproj.repository.TransactionRepository;
import com.prathamproj.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class BankStatement {

	private TransactionRepository transactionRepository;
	private UserRepository userRepository;
	private EmailService emailService;

	private static final String FILE = "/Users/prathamtanpure/Downloads/Banking-System/bankStatement.pdf";

	public List<Transaction> generateStatement(String accountNumber, String startDate, String endDate)
			throws FileNotFoundException, DocumentException {

		LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

		List<Transaction> transactionList = transactionRepository.findAll().stream()
				.filter(tx -> tx.getAccountNumber().equals(accountNumber))
				.filter(tx -> !tx.getCreatedAt().isBefore(start) && !tx.getCreatedAt().isAfter(end)).toList();

		User user = userRepository.findByAccountNumber(accountNumber);
		String customerName = user.getFirstName() + " " + user.getLastName() + " " + user.getOtherName();

		// PDF Setup
		Rectangle statementSize = new Rectangle(PageSize.A4);
		Document document = new Document(statementSize);
		OutputStream outputStream = new FileOutputStream(FILE);
		PdfWriter.getInstance(document, outputStream);
		document.open();

		// Fonts
		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.WHITE);
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
		Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
		Font boldCell = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);

		// Bank Name Header
		PdfPTable bankHeader = new PdfPTable(1);
		PdfPCell bankName = new PdfPCell(new Phrase("Bank of Pratham", titleFont));
		bankName.setBackgroundColor(BaseColor.BLUE);
		bankName.setPadding(20f);
		bankName.setBorder(0);
		bankName.setHorizontalAlignment(Element.ALIGN_CENTER);
		bankHeader.addCell(bankName);
		document.add(bankHeader);

		// Info Table (Start Date, End Date, Statement Title, Name, Address)
		PdfPTable infoTable = new PdfPTable(2);
		infoTable.setWidthPercentage(100);
		infoTable.setSpacingBefore(10f);
		infoTable.setSpacingAfter(10f);

		infoTable.addCell(createCell("Start Date: " + startDate, cellFont, Element.ALIGN_LEFT));
		infoTable.addCell(createCell("End Date: " + endDate, cellFont, Element.ALIGN_RIGHT));

		PdfPCell statementTitle = new PdfPCell(new Phrase("STATEMENT OF ACCOUNT", boldCell));
		statementTitle.setColspan(2);
		statementTitle.setBorder(0);
		statementTitle.setPadding(10f);
		statementTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		infoTable.addCell(statementTitle);

		PdfPCell nameCell = new PdfPCell(new Phrase("Customer Name: " + customerName, cellFont));
		nameCell.setColspan(2);
		nameCell.setBorder(0);
		nameCell.setPadding(8f);
		infoTable.addCell(nameCell);

		PdfPCell addressCell = new PdfPCell(new Phrase("Customer Address: " + user.getAddress(), cellFont));
		addressCell.setColspan(2);
		addressCell.setBorder(0);
		addressCell.setPadding(8f);
		infoTable.addCell(addressCell);

		document.add(infoTable);

		// Transaction Table
		PdfPTable transactionTable = new PdfPTable(4);
		transactionTable.setWidthPercentage(100);
		transactionTable.setSpacingBefore(10f);
		transactionTable.setWidths(new float[] { 2, 3, 3, 2 });

		transactionTable.addCell(createHeaderCell("DATE"));
		transactionTable.addCell(createHeaderCell("TRANSACTION TYPE"));
		transactionTable.addCell(createHeaderCell("TRANSACTION AMOUNT"));
		transactionTable.addCell(createHeaderCell("STATUS"));

		double totalCredit = 0;
		double totalDebit = 0;

		for (Transaction tx : transactionList) {
			transactionTable.addCell(createCell(tx.getCreatedAt().toString(), cellFont));
			transactionTable.addCell(createCell(tx.getTransactionType(), cellFont, Element.ALIGN_CENTER));
			transactionTable.addCell(createCell(String.format("%.2f", tx.getAmount()), cellFont, Element.ALIGN_RIGHT));
			transactionTable.addCell(createCell(tx.getStatus(), cellFont, Element.ALIGN_CENTER));

			if ("CREDIT".equalsIgnoreCase(tx.getTransactionType())) {
				totalCredit += tx.getAmount().doubleValue();
			} else if ("DEBIT".equalsIgnoreCase(tx.getTransactionType())) {
				totalDebit += tx.getAmount().doubleValue();
			}
		}

		document.add(transactionTable);

		// Totals
		PdfPTable totals = new PdfPTable(2);
		totals.setWidthPercentage(50);
		totals.setSpacingBefore(15f);
		totals.setHorizontalAlignment(Element.ALIGN_RIGHT);

		totals.addCell(createCell("Total Credit:", boldCell, Element.ALIGN_LEFT));
		totals.addCell(createCell(String.format("%.2f", totalCredit), boldCell, Element.ALIGN_RIGHT));
		totals.addCell(createCell("Total Debit:", boldCell, Element.ALIGN_LEFT));
		totals.addCell(createCell(String.format("%.2f", totalDebit), boldCell, Element.ALIGN_RIGHT));

		document.add(totals);

		// Footer
		PdfPTable footer = new PdfPTable(1);
		footer.setSpacingBefore(30f);
		PdfPCell note = new PdfPCell(new Phrase(
				"This is a system-generated statement. For help, contact pratham3778@gmail.com.", cellFont));
		note.setBorder(0);
		note.setHorizontalAlignment(Element.ALIGN_CENTER);
		note.setPadding(15f);
		footer.addCell(note);
		document.add(footer);

		document.close();

		// Email PDF
		EmailDetails emailDetails = EmailDetails.builder().recipient(user.getEmail())
				.subject("STATEMENT OF ACCOUNT").messageBody("Kindly find your requested account statement attached!")
				.attachment(FILE).build();

		emailService.sendEmailWithAttachement(emailDetails);

		return transactionList;
	}

	private PdfPCell createCell(String content, Font font, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setPadding(8f);
		cell.setBorder(0);
		cell.setHorizontalAlignment(alignment);
		return cell;
	}

	private PdfPCell createCell(String content, Font font) {
		return createCell(content, font, Element.ALIGN_LEFT);
	}

	private PdfPCell createHeaderCell(String content) {
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.WHITE);
		PdfPCell header = new PdfPCell(new Phrase(content, headerFont));
		header.setBackgroundColor(BaseColor.BLUE);
		header.setHorizontalAlignment(Element.ALIGN_CENTER);
		header.setPadding(10f);
		return header;
	}
}

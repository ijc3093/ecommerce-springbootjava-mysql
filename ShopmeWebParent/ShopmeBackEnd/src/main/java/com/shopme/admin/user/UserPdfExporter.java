package com.shopme.admin.user;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.shopme.common.entity.User;

public class UserPdfExporter extends AbstractExporter{

	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/pdf", ".pdf");
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph paragraph = new Paragraph("List of User", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		writeTableHeader(table);
		
		document.add(paragraph);
		document.add(table);
		document.close();
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("USER ID", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Photos", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("E-mail", font));
		
		cell.setPhrase(new Phrase("First Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Last Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Roles", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Enabled", font));
		table.addCell(cell);
		
	}

}
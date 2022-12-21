package com.team.serveur_app.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.team.serveur_app.model.plat.Plat;
import com.team.serveur_app.model.reservation.ReservationDAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GeneratePdf
{

    public static void print(ReservationDAO reservation) {
        //created PDF document instance
        Document doc = new Document();
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(
                    "D:\\"+"reservation"+reservation.getId()+".pdf"));

            System.out.println("PDF created.");


            doc.open();
            doc.add(new Paragraph("************************************"));
            System.out.println(reservation.getListPlat());
            for(Plat plat : reservation.getListPlat()) {
                doc.add(new Paragraph(String.format("%"+(-30)+"s", plat.getNom()) + plat.getPrice()));
            }
            doc.add(new Paragraph("************************************"));
            doc.add(new Paragraph("Total : "+reservation.getPrice() + " DH"));
            doc.add(new Paragraph("************************************"));
            doc.add(new Paragraph(String.format("%"+(-30)+"s","Serveur :")+reservation.getServeur().getNom()));
            doc.add(new Paragraph("Date : "+String.format("%"+(-30)+"s", reservation.getDate())));
            doc.add(new Paragraph("Merci pour votre visite"));
            doc.close();
            writer.close();
        }
        catch (DocumentException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}  
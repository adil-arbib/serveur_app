package com.team.serveur_app.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfBody;
import com.itextpdf.text.pdf.PdfWriter;
import com.team.serveur_app.model.plat.Plat;
import com.team.serveur_app.model.reservation.ReservationDAO;
import org.w3c.dom.Text;

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
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(
                    "D:\\"+"reservation"+reservation.getId()+".pdf"));

            System.out.println("PDF created.");


            doc.open();
            doc.add(new Paragraph(getContent(reservation)));
            doc.close();
            writer.close();
        }
        catch (DocumentException | FileNotFoundException e)
        {
            e.printStackTrace();
        }





    }

    private static String show(String str) {
        return str + " ".repeat(110 - str.length());
    }


    private static String getContent(ReservationDAO res) {
        String content = "";
        content += "*".repeat(110);
        for(Plat plat : res.getListPlat()) {
            content += "\n" + show(plat.getNom()) + plat.getPrice() + " DH";
        }
        content += "\n" + "*".repeat(110);

        content += "\n" + show("Total :") + res.getPrice() + " DH";
        content += "\n" + "*".repeat(110);

        content += "\n" + show("Serveur :") + res.getServeur().getNom();
        content += "\n" + show("Table :") + res.getTable().getNum();
        content += "\n" + show("Date :") + res.getDate();
        content += "\n" +"*".repeat(110);

        content+= "\n"+"Merci pour votre visite";

        return content;
    }






}  
package com.rdms.controller;

import com.rdms.model.DistributionDetails;
import com.rdms.model.RationDistribution;
import com.rdms.service.DistributionDetailService;
import com.rdms.service.RationDistributionService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/report")
public class DownloadReport {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    @Autowired
    private RationDistributionService rationDistributionService;

    @Autowired
    private DistributionDetailService distributionDetailService;


    @GetMapping("/download/{stockId}")
    public ResponseEntity<Resource> exportToExcel(@PathVariable("stockId") String stockIds ) throws IOException {


        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

         workbook = new XSSFWorkbook();

        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);
        XSSFFont font = workbook.createFont();
        CellStyle style = workbook.createCellStyle();


        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        Integer monthId = Integer.valueOf(stockIds);
        List<RationDistribution> rationDistributions = rationDistributionService.findByStockId(monthId);

        RationDistribution mode =   rationDistributions.get(0);
        Set<DistributionDetails>  ddetails = mode.getDetails();
        int count = 0;
        createCell(row, count++, "S.N", style);
        createCell(row, count++, "Date", style);

        createCell(row, count++, "Card Number", style);
        createCell(row, count++, "Card Holder", style);
        createCell(row, count++, "Father/Husband", style);
        createCell(row, count++, "Unit", style);
        createCell(row, count++, "Card Type", style);

        for (DistributionDetails model : ddetails){
            createCell(row, count++, model.getStockItem().getItemName(), style);

        }
        createCell(row, count++, "Amount", style);

        createCell(row, count++, "Signature", style);




        int rowCount = 1;

        CellStyle style2 = workbook.createCellStyle();
        CellStyle stylePink = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setFontHeight(14);
        style2.setFont(font2);
        style2.setAlignment(HorizontalAlignment.CENTER);

        stylePink.setFillBackgroundColor(IndexedColors.PINK.getIndex());
       int sn=1;

        for (RationDistribution rationDistribution : rationDistributions) {
            CellStyle commonStyle =style2;
            Row row2 = sheet.createRow(rowCount++);
            int columnCount = 0;


            createCell(row2, columnCount++, sn, commonStyle);
            createCell(row2, columnCount++, dateFormatter.format(Date.from(rationDistribution.getDistributedOn())),commonStyle );
            createCell(row2, columnCount++, rationDistribution.getRationCard().getCardNumber(), commonStyle);
            createCell(row2, columnCount++, rationDistribution.getRationCard().getCardHolder(), commonStyle);
            createCell(row2, columnCount++, rationDistribution.getRationCard().getFatherOrHusband(), commonStyle);
            createCell(row2, columnCount++, rationDistribution.getRationCard().getUnit(), commonStyle);
            createCell(row2, columnCount++, rationDistribution.getRationCard().getCartType(), commonStyle);

            for (DistributionDetails model : rationDistribution.getDetails()){
                createCell(row2, columnCount++, new StringBuffer().append(model.getQuantity()).append(" Kg.").toString(), commonStyle);
            }
            createCell(row2, columnCount++, new StringBuffer().append(rationDistribution.getTotalAmount()).append(" Rs.").toString(), commonStyle);

            // to inset image
            createCell(row2, columnCount++, rationDistribution.getSignature(), commonStyle);

            sn =sn+1;
        }

        String fileName = mode.getStock().getMonthId().getMonthName()+"_"+mode.getStock().getDetails();
        String filename = fileName+".xlsx";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        InputStreamResource file = new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
        cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
        cell.setCellValue((Boolean) value);
        } else if(value instanceof  byte []){
            if(value != null){
                int pictureID = workbook.addPicture((byte [])value, Workbook.PICTURE_TYPE_PNG);
                XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFClientAnchor signature = new XSSFClientAnchor();
                int col2 = columnCount;
                col2 = col2 +1;
                int row2 = row.getRowNum();
                row2 = row2+1;
                signature.setCol1(columnCount);
                signature.setCol2(col2);
                signature.setRow1(row.getRowNum());
                signature.setRow2(row2);
                drawing.createPicture(signature,pictureID);
            }

    }else {
        cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

}



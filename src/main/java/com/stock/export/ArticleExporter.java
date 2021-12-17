package com.stock.export;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.stock.entities.Article;
import com.stock.repositories.ArticleRepository;
import com.stock.utils.ApplicationConstants;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


@Service("articleExporter")
public class ArticleExporter implements FileExporter {

	@Autowired
	private ArticleRepository articleRepository;
	
	private final String FILE_NAME = "Liste_des_articles.xls";
	
	@Override
	public boolean exportDataToExcel(HttpServletResponse response, String fileName, String encodage) {
		
		if(!StringUtils.hasLength(fileName)) {
			fileName = FILE_NAME;
		}
		
		if(!StringUtils.hasLength(encodage)) {
			encodage = ApplicationConstants.DEFAULT_ENCODAGE;
		}
		
		response.setContentType(ApplicationConstants.EXCEL_CONTENT_TYPE);
		response.setHeader(ApplicationConstants.CONTENET_DISPOSITION, "attachment; filename=" + fileName);
		
		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding(encodage);
		
		try {
			
			WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream(), workbookSettings);
			WritableSheet sheet = workbook.createSheet(fileName, 0);
			
			/**
			 * Sheet Header
			 */
			
			Label labelCode = new Label(0, 0, ApplicationConstants.CODE_ARTICLE);
			labelCode.setCellFeatures(new WritableCellFeatures());
			labelCode.getCellFeatures().setComment("");
			sheet.addCell(labelCode);
			
			Label labelDesignation = new Label(1, 0, ApplicationConstants.DESIGNATION);
			labelDesignation.setCellFeatures(new WritableCellFeatures());
			labelDesignation.getCellFeatures().setComment("");
			sheet.addCell(labelDesignation);
			
			Label labelPrixUnitHt = new Label(2, 0, ApplicationConstants.PRIX_UNIT_HT);
			labelPrixUnitHt.setCellFeatures(new WritableCellFeatures());
			labelPrixUnitHt.getCellFeatures().setComment("");
			sheet.addCell(labelPrixUnitHt);
			
			Label labelPrixUnitTtc = new Label(3, 0, ApplicationConstants.PRIX_UNIT_TTC);
			labelPrixUnitTtc.setCellFeatures(new WritableCellFeatures());
			labelPrixUnitTtc.getCellFeatures().setComment("");
			sheet.addCell(labelPrixUnitTtc);
			
			Label labelTauxTva = new Label(4, 0, ApplicationConstants.TVA);
			labelTauxTva.setCellFeatures(new WritableCellFeatures());
			labelTauxTva.getCellFeatures().setComment("");
			sheet.addCell(labelTauxTva);
			
			Label labelCategorie = new Label(5, 0, ApplicationConstants.CATEGORIE);
			labelCategorie.setCellFeatures(new WritableCellFeatures());
			labelCategorie.getCellFeatures().setComment("");
			sheet.addCell(labelCategorie);
			
			
			int currentRow = 1;
			
			List<Article> articles = articleRepository.findAll();
			
			if(articles != null && !articles.isEmpty()) {
				/**
				 * Write articles in sheet
				 */
				for(Article article : articles) {
					sheet.addCell(new Label(0, currentRow, article.getCodeArticle()));
					sheet.addCell(new Label(1, currentRow, article.getDesignation()));
					sheet.addCell(new Label(2, currentRow, article.getPrixUnitaireHT().toString()));
					sheet.addCell(new Label(3, currentRow, article.getPrixUnitaireTTC().toString()));
					sheet.addCell(new Label(4, currentRow, article.getTauxTva().toString()));
					sheet.addCell(new Label(5, currentRow, article.getCategory().getCode()));
					currentRow++;
				}
				CellView cellView = new CellView();
				cellView.setAutosize(true);
//				cellView.setSize(30 * 340);
				sheet.setColumnView(0, cellView);
				sheet.setColumnView(1, cellView);
				sheet.setColumnView(2, cellView);
				sheet.setColumnView(3, cellView);
				sheet.setColumnView(4, cellView);
				sheet.setColumnView(5, cellView);
				
				/**
				 * write to exce sheet
				 */
				workbook.write();
				/**
				 * Closing the workbook
				 */
				workbook.close();
				
				response.getOutputStream().flush();
				response.getOutputStream().close();
				
			}
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public boolean importDatFromExcel() {
		
		return false;
	}

}

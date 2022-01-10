package com.pregnant_mannage.controller;

import com.pregnant_mannage.entity.Exam_paper;
import com.pregnant_mannage.entity.User;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller

public class CExcel {

    @Autowired
    private com.pregnant_mannage.service.Exam_paperService Exam_paperService;

    @GetMapping("/toHtml")

    String test(HttpServletRequest request) {

        return "excelImport";

    }
//处理文件上传



    @RequestMapping(value = "add_papers_pc", method = RequestMethod.POST)

    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request , Model model) {

        System.out.println("add_papers_pc");
        String contentType = file.getContentType();

        String fileName = file.getOriginalFilename();

        if (file.isEmpty()) {

            return "文件为空！";

        }

        try {

//根据路径获取这个操作excel的实例

            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            //根据页面index 获取sheet页

            XSSFSheet sheet = wb.getSheetAt(0);

//实体类集合

            List<Exam_paper> Exam_papers = new ArrayList<>();

            XSSFRow row = null;

//循环sesheet页中数据从第二行开始，第一行是标题

            System.out.println(sheet.getPhysicalNumberOfRows()-1);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows()-1; i++) {

//获取每一行数据

                row = sheet.getRow(i);

               Exam_paper paper = new Exam_paper();
               paper.setPaperid(row.getCell(0).getStringCellValue());
                System.out.println(paper.getPaperid());
                paper.setExamid(String.valueOf(row.getCell(1).getStringCellValue()));
                System.out.println(paper.getExamid());
                paper.setDescribes(row.getCell(2).getStringCellValue());
                System.out.println(paper.getDescribes());
                paper.setPapaeratttion(row.getCell(3).getStringCellValue());
                System.out.println(paper.getPapaeratttion());
                paper.setDoctorid(row.getCell(4).getRawValue());
                paper.setUserid(row.getCell(5).getRawValue());


               paper.setPapaertime(String.valueOf(row.getCell(6).getStringCellValue()));
                System.out.println(paper.getPapaertime());
//                paper.setPapaertime(df.parse(df.format(HSSFDateUtil.getJavaDate(row.getCell(6).getStringCellValue()))));




                paper.setSrc(row.getCell(7).getStringCellValue());
                paper.setWeight(String.valueOf(row.getCell(8).getStringCellValue()));
                paper.setFetal_heart(String.valueOf(row.getCell(9).getStringCellValue()));
                paper.setBlood_pressure(String.valueOf(row.getCell(10).getStringCellValue()));
                paper.setUterine_height(String.valueOf(row.getCell(11).getStringCellValue()));
                paper.setAbdominal_girth(String.valueOf(row.getCell(12).getStringCellValue()));



                //                System.out.println(data.getExamid());
//                data.setExamid(Integer.valueOf((int) row.getCell(0).getNumericCellValue()));
//                data.setName(row.getCell(1).getStringCellValue());

               //                data.setAge(Integer.valueOf((int) row.getCell(3).getNumericCellValue()));
                Exam_papers.add(paper);



            }

//循环展示导入的数据，实际应用中应该校验并存入数据库

            for (Exam_paper paper : Exam_papers) {

//                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                boolean issuccess=Exam_paperService.insertintoexam_paper(paper);
                System.out.println("ID:"+paper.getPaperid());



//                        +" name:"+imdata.getName()+" createDate:"+df.format(imdata.getCreateDate())+" age:"+imdata.getAge());

            }

            model.addAttribute("excel_issuccuse","true");
            System.out.println("添加成功");

        } catch (Exception e) {


            e.printStackTrace();
            model.addAttribute("excel_issuccuse","false");
            System.out.println("添加失败,请检查");
            return "redirect:show_addpaper_pc";

        }



        return "redirect:show_addpaper_pc";

    }

}
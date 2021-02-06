# spring-boot-starter-easy-ocr


本项目为了方便大家操作ocr而生，本项目中已收录了百度ocr和腾讯ocr。目前仅支持身份证和银行卡识别
### 具体使用方法：
## 1. 引入maven依赖
        <dependency>
           <groupId>com.easy.ocr</groupId>
            <artifactId>spring-boot-starter-easy-ocr</artifactId>
            <version>1.0.1</version>
        </dependency>

## 2. 引入 repository

     <repositories>
        <repository>
         <id>github</id>
           <name>GitHub autumn Apache Maven Packages</name>
           <url>https://maven.pkg.github.com/AutKevin/maven-repo/</url>
           <snapshots>
            <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
               </snapshots>
            </repository>
       </repositories>
    

## 2.引入EasyOcrUtil
     @Autowired private EasyOcrUtil easyOcrUtil; 

## 3.调用api即可
       IdCard idCard = easyOcrUtil.showBack(new File("E:\\ocr\\ids\\timg.jpg"));
       System.out.println(idCard);



### API

# 1.解析银行卡


    /** 
      * 解析银行卡 
      * @param img 
      * @return */ 
  
      BankCard showBankNo(File img)
#####   BankCard
           //银行卡号
            private String cardNo;
            //卡类型
            private String cardType;
            //卡名
            private String cardName;
            //银行
            private String cardBank;
            //有效期
            private String cardValidDate;


# 2.解析身份证前面

    /**
    * 解析身份证前面 
    * @param img 
    * @return 
    */
    IdCard showFront(File img);

# 3. 解析身份证后面 

    /** 
    * 解析身份证后面 
    * @param img 
    * @return */
    IdCard showBack(File img);
##### IdCard
            //姓名
            private String name;
            //性别
            private String sex;
            //民族
            private String nation;
            //出生日期
            private String birth;
            //地址
            private String address;
            //身份证号
            private String cardNo;  

### 提示：web中使用


     @Autowired private EasyOcrUtil easyOcrUtil;

     @RequestMapping(value = "/uploadIdCard")
     @ResponseBody
     public ExecResult uploadIdCardFront(@RequestParam("file") MultipartFile file) {
     try {   
            //将MultipartFile转为File
           File f = transferToFile(file);   
           if (f == null) {     
            return ExecResult.fail("文件不能为空"); 
        
        } 
        //调用ocr工具        
        IdCard idCard = easyOcrUtil.showIdCard(f);  
        if (idCard == null) {      
           idCard = new IdCard(); 
        }
         idCard.setUrl("this is a web URL");
         f.delete(); 
         return ExecResult.success(idCard);
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("====== 上传身份证异常! ======{}",
             e.getMessage());   
        return ExecResult.fail("上传身份证异常！" +
         e.getMessage());        
         }
     }
  
    private File transferToFile(MultipartFile multipartFile) {
        //选择用缓冲区来实现这个转换即使用java 创建的临时文件
        //使用 MultipartFile.transferto()方法 。
        File file = null;
        try {   
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split(".");
            file=File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();        
        } catch (IOException e) {
             e.printStackTrace();
        }
        return file;
    }


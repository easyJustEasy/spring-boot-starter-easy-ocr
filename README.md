# spring-boot-starter-easy-ocr


本项目为了方便大家操作ocr而生，本项目中已收录了百度ocr和腾讯ocr。目前仅支持身份证和银行卡识别.
新！！！！增加了本地大模型识别，可以不使用百度和腾讯api~
！！！ 强烈建议将项目复制到自己项目中做二次开发！！！！，因为博主没有太多时间维护

### 具体使用方法：
## 1. 引入maven依赖
        <dependency>
           <groupId>com.easy.ocr</groupId>
            <artifactId>spring-boot-starter-easy-ocr</artifactId>
             <version>2.0.0</version>
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
### 2. 在你的项目中做简单配置 application.yml
```

server:
  port: 8081
spring:
  easy:
    ocr:
      # 百度云配置 去百度云申请自己的账户获取app-id和api-key和secret-key
      baidu:
        app-id: 
        api-key: 
        secret-key: 
        open: false
      # 腾讯云配置 去百度云申请自己的账户获取app-id和api-key和secret-key
      qq:
         app-id: 
         secret-key: 
         secret-id: 
         bucket-name:
         open: false
```

## 3.引入EasyOcrUtil
     @Autowired private EasyOcrUtil easyOcrUtil; 

## 4.调用api即可
       IdCard idCard = easyOcrUtil.showBack(new File("E:\\ocr\\ids\\timg.jpg"));
       System.out.println(idCard);

## 5.说明： 程序默认开启的是大模型识别，所以百度和qq的配置可以都是open=false

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


### 示例说明
启动example项目com.easy.ocr.App
访问： http://localhost:9999/


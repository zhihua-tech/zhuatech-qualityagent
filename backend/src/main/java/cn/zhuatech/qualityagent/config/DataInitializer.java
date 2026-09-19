/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.qualityagent.config;
import cn.zhuatech.qualityagent.model.*; import cn.zhuatech.qualityagent.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder; import java.time.LocalDate; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration public class DataInitializer {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Bean CommandLineRunner seed(OperatingUnitRepository units,WorkRecordRepository records,ResourceRegisterRepository resources,ReviewRecordRepository reviews,UserRepository users,PasswordEncoder encoder){return args->{if(units.count()>0)return;
  OperatingUnit first=units.save(new OperatingUnit("QA-MFG1","制造质量一组","质量管理中心",2200)),second=units.save(new OperatingUnit("QA-FINAL","成品质量组","质量管理中心",1600)),third=units.save(new OperatingUnit("QA-SUP","供应商质量组","供应链质量",1200));
  WorkRecord a=records.save(new WorkRecord("CAPA-260808-018","MAT-CTRL-A12","控制器端子压接不良改进",first,12,7,4,LocalDate.now().plusDays(2),WorkRecord.Status.RUNNING,"8D-V3")); WorkRecord b=records.save(new WorkRecord("CAPA-260808-012","PKG-LABEL-07","包装标签错贴防错改善",second,8,8,0,LocalDate.now().plusDays(0),WorkRecord.Status.COMPLETED,"8D-V2")); WorkRecord c=records.save(new WorkRecord("CAPA-260808-021","SUP-CONN-22","来料连接器尺寸波动",third,10,5,2,LocalDate.now().plusDays(3),WorkRecord.Status.RELEASED,"8D-V1"));
  resources.saveAll(List.of(new ResourceRegister("DATA-QMS-01","QMS 质量记录视图",second,ResourceRegister.Status.RUNNING,96),new ResourceRegister("DATA-MES-02","MES 过程参数视图",first,ResourceRegister.Status.RUNNING,91),new ResourceRegister("GUARD-RELEASE-03","产品放行审批通道",second,ResourceRegister.Status.ALARM,73)));
  reviews.saveAll(List.of(new ReviewRecord("REV-QA-028",a,"根因准确性",26,2,ReviewRecord.Result.PENDING,"叶峥"),new ReviewRecord("REV-QA-017",b,"措施有效性",18,0,ReviewRecord.Result.PASSED,"苏青禾"),new ReviewRecord("REV-QA-039",c,"放行合规",14,2,ReviewRecord.Result.FAILED,"陈屿")));
  String demo=encoder.encode("Demo@2026");
  users.saveAll(List.of(new UserAccount("operator",demo,"苏青禾",UserAccount.Role.DOMAIN_USER,"QA-MFG1"),new UserAccount("planner",demo,"叶峥",UserAccount.Role.DOMAIN_OPERATOR,null),new UserAccount("quality",demo,"评测负责人",UserAccount.Role.QUALITY,null),new UserAccount("admin",encoder.encode("ZhuaTech@2026"),"系统管理员",UserAccount.Role.ADMIN,null)));
 };}}


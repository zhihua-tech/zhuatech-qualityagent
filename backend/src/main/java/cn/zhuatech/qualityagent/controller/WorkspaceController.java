/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.qualityagent.controller;
import cn.zhuatech.qualityagent.agent.AgentRuntime;
import cn.zhuatech.qualityagent.common.ApiResponse;
import cn.zhuatech.qualityagent.dto.QualityAgentDto.*;
import cn.zhuatech.qualityagent.service.QualityAgentService;
import cn.zhuatech.qualityagent.service.RootCauseService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/shopfloor") @PreAuthorize("hasAnyRole('DOMAIN_USER','ADMIN')")
public class WorkspaceController {
 private final QualityAgentService service; private final AgentRuntime runtime; private final RootCauseService domainAgent;
 public WorkspaceController(QualityAgentService service,AgentRuntime runtime,RootCauseService domainAgent){this.service=service;this.runtime=runtime;this.domainAgent=domainAgent;}
 @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.shopfloorDashboard());}
 @PostMapping("/work-orders/{id}/reports") public ApiResponse<ReportResult> report(@PathVariable Long id,@Valid @RequestBody ReportRequest request){return ApiResponse.ok("反馈提交成功",service.report(id,request));}
 @PostMapping("/agent-preview") public ApiResponse<AgentRuntime.AgentResult> preview(@RequestBody Map<String,String> body){return ApiResponse.ok(runtime.run(new AgentRuntime.AgentRequest(body.getOrDefault("objective","分析当前业务事项"),Map.of("mode","demo","approval","required"))));}
 @PostMapping("/root-cause") public ApiResponse<RootCauseService.CauseResult> domainAction(@Valid @RequestBody RootCauseService.CauseRequest request){return ApiResponse.ok("质量根因辅助分析完成",domainAgent.analyze(request));}
}


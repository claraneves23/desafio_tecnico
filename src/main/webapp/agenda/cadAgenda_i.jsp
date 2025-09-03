<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title><s:text name="label.titulo.pagina.cadastro.agenda"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">

		<div class="container">
			<s:form action="%{agendaVo.idAgenda == null ? 'novaAgendas' : 'atualizarAgendas'}">

				
				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todasAgendas" var="todas"/>
								<a href="${todas}" class="btn btn-success" >
									<s:text name="label.agendas"/>
								</a>
							</div>
							
							<div class="col-sm">
								 <s:if test="agendaVo.idAgenda == null || agendaVo.idAgenda == ''">
        							<s:text name="label.novo.agenda"/>
    							</s:if>
    							<s:else>
        							<s:text name="label.editar.agenda"/>
    							</s:else>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="idAgenda" class="col-sm-1 col-form-label text-center"> Código:
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="idAgenda" name="agendaVo.idAgenda" value="%{agendaVo.idAgenda}" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nomeAgenda" class="col-sm-1 col-form-label text-center">
								<s:text name="label.nome.agenda"/>:
							</label>	

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="nomeAgenda" name="agendaVo.nomeAgenda" value="%{agendaVo.nomeAgenda}"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="periodoDisponivel" class="col-sm-1 col-form-label text-center">
								<s:text name="label.periodo"/>:
							</label>	

							<div class="col-sm-5">
								<s:select 
									cssClass="form-control" 
									id="periodoDisponivel" 
									name="agendaVo.periodoDisponivel"
									list="#{ getText('label.manha'): getText('label.manha'),  getText('label.tarde'): getText('label.tarde'), getText('label.ambos'): getText('label.ambos')}"
									value="%{agendaVo.periodoDisponivel}"
									headerKey="" 
									headerValue="%{getText('label.escolha')}"
								/>							
							</div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">
								<s:text name="label.salvar"/>
							</button>
							<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">
								<s:text name="label.limpar.formulario"/>
							</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
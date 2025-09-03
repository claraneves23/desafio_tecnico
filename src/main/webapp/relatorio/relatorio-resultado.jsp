<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Relatório de Compromissos</title>
    <link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
    <style>
        @media print {
            .no-print { display: none !important; }
            body { background: white !important; }
        }
    </style>
</head>
<body class="bg-secondary">
    <div class="container mt-5">
        <div class="card no-print">
            <div class="card-header">
                <h3>Relatório de Compromissos</h3>
                <p>Período: <s:property value="filtro.dataInicial"/> até <s:property value="filtro.dataFinal"/></p>
            </div>
        </div>
        
        <div class="card mt-3">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Funcionário (Código/Nome)</th>
                                <th>Agenda (Código/Nome)</th>
                                <th>Data</th>
                                <th>Hora</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="relatorio">
                                <tr>
                                    <td>${idFuncionario} - ${nomeFuncionario}</td>
                                    <td>${idAgenda} - ${nomeAgenda}</td>
                                    <td>${dataCompromisso}</td>
                                    <td>${horaCompromisso}</td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                
                <div class="no-print mt-3">
                    <button onclick="window.print()" class="btn btn-primary">Imprimir</button>
                    <s:url action="relatorio" var="voltar"/>
                    <a href="${voltar}" class="btn btn-secondary">Voltar</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
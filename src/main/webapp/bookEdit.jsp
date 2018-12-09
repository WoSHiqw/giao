<%@ page import="com.oraclewdp.ddbookmarket.model.Book" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>修改</title>
<!--告诉浏览器不要缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="bower_components/bootswatch/dist/cerulean/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.custom-file-label::after {
	content: "浏览"
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card border-primary mb-3">
					<div class="card-header">
						<form method="post"  autocomplete="off" action="doBookEdit" enctype="multipart/form-data">
							<%
								Book book= (Book) request.getAttribute("book");
							%>
							<input type="hidden" name="id"value="<%=book.getId()%>">
							<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label text-right">大类</label>
								<div class="col-sm-10">
									<select name="bid" class="form-control"id="inputBid">
									<option>选择</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputSid" class="col-sm-2 col-form-label text-right">小类名</label>
								<div class="col-sm-10">
									
									<select class="form-control" id="inputSid" placeholder="小类名" name="sid">
									<option value="-1">选择</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputsid" class="col-sm-2 col-form-label text-right">书名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName" placeholder="书名" name="name" value="<%=book.getName()%>">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputprice" class="col-sm-2 col-form-label text-right">价格</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputprice" placeholder="价格" name="price"value="<%=book.getPrice()%>">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputauthor" class="col-sm-2 col-form-label text-right">作者</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputauthor" placeholder="作者" name="author"value="<%=book.getAuthor()%>">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputcbs" class="col-sm-2 col-form-label text-right">出版社</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputcbs" placeholder="出版社" name="cbs"value="<%=book.getCbs()%>">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputData" class="col-sm-2 col-form-label text-right">出版日期</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputData" placeholder="出版日期" name="cbDate"readonly="readonly" value="<%=book.getCbDate()%>">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputdescri" class="col-sm-2 col-form-label text-right">简介</label>
								<div class="col-sm-10">
									<textarea class="form-control"id="inputdescri" placeholder="出版社" name="descri"><%=book.getDescri()%></textarea>
								</div>
							</div>
							<div class="form-group row">
							<label for="inputPhoto" class="col-sm-2 clo-form-label text-right">图片</label>
							<div class="col-sm-10">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="inputPhoto" aria-describedby="inputGroupFileAddon04" name="photo"> <label class="custom-file-label" for="inputGroupFile04 ">请选择文件</label>

								</div>
								<img src="upload/<%=book.getPhoto()%>">
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<button type="submit" class="btn btn-primary text-right">修改</button>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
	<script type="text/javascript">
	function fillSel(types){
		for (var i = 0; i < types.length; i++) {
			var op=new Option(types[i].name,types[i].id);
			document.getElementById("inputBid").appendChild(op);
		}
        $("#inputBid").val('<%=request.getAttribute("bid")%>');
        $("#inputBid").trigger("change");
		}
		function fillSmallSel(types){
			document.getElementById("inputSid").innerHTML="";
			for (var i = 0; i < types.length; i++) {
				var op=new Option(types[i].name,types[i].id);
				document.getElementById("inputSid").appendChild(op);
			}
            $("#inputSid").val('<%=book.getSid()%>');
			}

		$.ajax({
			url:"findAllBigType",
			dataType:"jsonp",
			jsonpCallback:"fillSel"
			});
		$("#inputBid").change(function(){
			$.ajax({
				url:"findAllSmallType",
				dataType:"jsonp",
				data:"bid="+$(this).val(),
				jsonpCallback:"fillSmallSel"
				});
			});
		$('#inputData').datepicker({
			language : 'zh-CN',
			format : 'yyyy-mm-dd',
			autoclose : true,
			defaultViewDate : {
				year : new Date().getFullYear() - 18
			}
		});
	</script>
</body>
</html>
@/*
表单中radio框标签中各个参数的说明:

id : radio的id
name : radio的名称
value : radio的value
valueNm : radio的valueNm
@*/
<div class="col-sm-4" align="center"
     style="height:30px;border: 1px solid #999999;background-color: #F5F5F5">
    <input type="radio" name="${name}" id="${id}" value="${value}"
           @if(isNotEmpty(checked)){
           checked="checked"
           @}
    >${valueNm}
</div>



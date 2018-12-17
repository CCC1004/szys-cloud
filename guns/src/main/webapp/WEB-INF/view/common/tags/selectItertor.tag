@/*
    select标签中各个参数的说明:
    name : select的名称
    id : select的id
    underline : 是否带分割线
@*/
<div class="form-group">
    <label class="col-sm-4 col-md-4 control-label">${name}</label>
    <div class="col-sm-8 col-md-8">
        <select class="form-control" id="${id}" name="${id}"
                @if(isNotEmpty(style)){
                style="${style}"
                @}
                @if(isNotEmpty(disabled)){
                disabled="${disabled}"
                @}
        >
            ${tagBody!}
            @for(item in dictmap){
                <option value="${item.key}">${item.value}</option>
            @}
        </select>
        @if(isNotEmpty(hidden)){
            <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}">
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}



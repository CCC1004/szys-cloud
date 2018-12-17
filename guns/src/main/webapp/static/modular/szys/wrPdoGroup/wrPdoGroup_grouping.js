
/**
 * 初始化水源分组详情对话框
 */
var Grouping = {
    id: "groupingTable",	//表格id
    table: null,
    wrPdoGroupInfoData : {}
};


/**
 * 关闭此对话框
 */
Grouping.close = function() {
    parent.layer.close(window.parent.WrPdoInfo.layerIndex);
}


/**
 * 初始化表格的列
 */
Grouping.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '取水口代码', field: 'uuid', visible: false, align: 'center', valign: 'middle'},
        {title: '序号', field: 'sn', visible: false, align: 'center', valign: 'middle'},
        {title: '取水口名称', field: 'intNm', visible: true, align: 'center', valign: 'middle'},
        {title: '取水口许可证编码', field: 'wpcCd', visible: false, align: 'center', valign: 'middle'},
        {title: '取水许可证号', field: 'wpcSn', visible: true, align: 'center', valign: 'middle'},
        {title: '取水许可证其实日期', field: 'intBd', visible: false, align: 'center', valign: 'middle'},
        {title: '取水许可证结束日期', field: 'intDl', visible: false, align: 'center', valign: 'middle'},
        {title: '取用水户名称', field: 'wiuNm', visible: true, align: 'center', valign: 'middle'},
        {title: '水源类型', field: 'sourTpNm', visible: true, align: 'center', valign: 'middle'},
        {title: '取水用途', field: 'intUseNm', visible: true, align: 'center', valign: 'middle'},
        {title: '取水行业', field: 'tradTpNm', visible: true, align: 'center', valign: 'middle'},
        {title: '地下水超采区类型', field: 'gwoaTpNm', visible: true, align: 'center', valign: 'middle'},
        {title: '管网覆盖', field: 'isPipeNm', visible: true, align: 'center', valign: 'middle'},
        {title: '税额等次', field: 'wrtrTpNm', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 分组，提交
 */
Grouping.groupSubmit = function (){

    var groupType = $("#groupType").val();
    var opt;

    if(groupType!='' && groupType!=null && groupType!='undefined'){

        if(groupType == '1'){
            opt = "";
        }else if(groupType == '2'){
            opt =  $("#wrWiuBSelect").val();
            if(opt=='' || opt==null){
                Feng.info("请选择取用水户!");
                return;
            }
        }else if(groupType == '3'){
            opt =  $("#wrWpcMSelect").val();
            if(opt=='' || opt==null){
                Feng.info("请选择取水许可证!");
                return;
            }
        }

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/wrPdoGroup/groupSubmit2", function(data){
                Feng.success("分组成功!");
                // window.parent.WrPdoGroup.table.refresh();//刷新父页面
                Grouping.close();//关闭当前页面
            },function(data){
                Feng.error("分组失败!" + data.responseJSON.message + "!");
            });
            ajax.set("groupType",groupType);
            ajax.set("opt",opt);
            ajax.start();
        }
        Feng.confirm("确认分组吗?", operation);

    }else{
        Feng.info("请选择分组类型!");
    }

}

Grouping.formParams = function() {
    var queryData = {};
    //默认按照行政区划分类
    var groupType = "1";
    queryData['groupType'] = groupType;
    //赋值
    $("#groupType").attr("value", groupType);
    return queryData;
}

$(function () {

    var defaultColunms = Grouping.initColumn();
    var table = new BSTable(Grouping.id, "/wrPdoGroup/groupListByType/", defaultColunms);
    table.setPaginationType("server");
    //查询条件参数
    table.setQueryParams(Grouping.formParams())
    Grouping.table = table.init();

    $("#wrAdB").css("background-color","#a7b1c2");



    //点击行政区划
    $("#wrAdB").click(function() {
        $("#wrAdB").css("background-color","#a7b1c2");
        $("#wrWiuBSelect").val("");
        $("#wrWpcMSelect").val("");

        var groupType = "1";
        $("#groupType").attr("value", groupType);

        var queryData = {};
        queryData['groupType'] = groupType;
        Grouping.table.refresh({query: queryData});

    });

    //取用水户select选中
    $("#wrWiuBSelect").change(function(){
        $("#wrAdB").css("background-color","");
        $("#wrWpcMSelect").val("");

        var groupType = "2";
        $("#groupType").attr("value", groupType);

        var opt = $("#wrWiuBSelect").val();
        var queryData = {};
        queryData['groupType'] = groupType;
        queryData['opt'] = opt;
        Grouping.table.refresh({query: queryData});
    });

    //取水许可证select选中
    $("#wrWpcMSelect").change(function(){
        $("#wrAdB").css("background-color","");
        $("#wrWiuBSelect").val("");

        var groupType = "3";
        $("#groupType").attr("value", groupType);

        var opt = $("#wrWpcMSelect").val();
        var queryData = {};
        queryData['groupType'] = groupType;
        queryData['opt'] = opt;
        Grouping.table.refresh({query: queryData});
    });



});
/**
 * 行政区划管理管理初始化
 */
var WrAdB = {
    id: "WrAdBTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WrAdB.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '行政区划代码', field: 'adCd', visible: true, align: 'center', valign: 'middle'},
            {title: '行政区划名称', field: 'adNm', visible: true, align: 'center', valign: 'middle'},
            {title: '行政区划简称', field: 'adShnm', visible: true, align: 'center', valign: 'middle'},
            {title: '行政区划面积', field: 'adA', visible: true, align: 'center', valign: 'middle'},
            {title: '是否启用', field: 'isEnable', visible: true, align: 'center', valign: 'middle'},
            {title: '时间戳', field: 'ts', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'nt', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
WrAdB.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WrAdB.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加行政区划管理
 */
WrAdB.openAddWrAdB = function () {
    var index = layer.open({
        type: 2,
        title: '添加行政区划管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wrAdB/wrAdB_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看行政区划管理详情
 */
WrAdB.openWrAdBDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '行政区划管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrAdB/wrAdB_update/' + WrAdB.seItem.adCd
        });
        this.layerIndex = index;
    }
};

/**
 * 删除行政区划管理
 */
WrAdB.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wrAdB/delete", function (data) {
            Feng.success("删除成功!");
            WrAdB.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wrAdBId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询行政区划管理列表
 */
WrAdB.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    WrAdB.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = WrAdB.initColumn();
    var table = new BSTable(WrAdB.id, "/wrAdB/list", defaultColunms);
    // table.setPaginationType("client");
    //分页使用server服务端（mybatis-plus的分页插件）
    table.setPaginationType("server");
    WrAdB.table = table.init();
});

/**
 * 取水许可证管理初始化
 */
var WrWpcM = {
    id: "WrWpcMTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WrWpcM.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '取水许可证代码', field: 'wpcCd', visible: true, align: 'center', valign: 'middle'},
        {title: '取水许可证编号', field: 'wpcSn', visible: true, align: 'center', valign: 'middle'},
        {title: '取水权人名称', field: 'wrpNm', visible: true, align: 'center', valign: 'middle'},
        {title: '行政区划', field: 'adNm', visible: true, align: 'center', valign: 'middle'},
        {title: '取用水户代码', field: 'wiuNm', visible: true, align: 'center', valign: 'middle'},
        {title: '取水起始日期', field: 'intBd', visible: true, align: 'center', valign: 'middle'},
        {title: '取水终止日期', field: 'intDl', visible: true, align: 'center', valign: 'middle'},
        {title: '监督管理单位代码', field: 'manOrgCd', visible: true, align: 'center', valign: 'middle'},
        {title: '证件状态', field: 'certCond', visible: true, align: 'center', valign: 'middle'},
        {title: '证书印刷编号', field: 'certPrtCd', visible: true, align: 'center', valign: 'middle'},
        {title: '领取日期', field: 'drawDt', visible: true, align: 'center', valign: 'middle'},
        {title: '领取人员', field: 'drawStaff', visible: true, align: 'center', valign: 'middle'},
        // {title: '时间戳', field: 'ts', visible: true, align: 'center', valign: 'middle'},
        // {title: '备注', field: 'nt', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
WrWpcM.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        WrWpcM.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加取水许可证
 */
WrWpcM.openAddWrWpcM = function () {
    var index = layer.open({
        type: 2,
        title: '添加取水许可证',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wrWpcM/wrWpcM_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看取水许可证详情
 */
WrWpcM.openWrWpcMDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '取水许可证详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrWpcM/wrWpcM_update/' + WrWpcM.seItem.wpcCd
        });
        this.layerIndex = index;
    }
};

WrWpcM.detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '取水许可证详情',
            area: ['800px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrWpcM/wrWpcM_detail/' + WrWpcM.seItem.wpcCd
        });
        this.layerIndex = index;
    }
}

/**
 * 删除取水许可证
 */
WrWpcM.delete = function () {
    if (this.check()) {
        var operation = function () {

            var ajax = new $ax(Feng.ctxPath + "/wrWpcM/delete", function (data) {
                Feng.success("删除成功!");
                WrWpcM.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("wrWpcMId", WrWpcM.seItem.wpcCd);


            ajax.start();
        }
        Feng.confirm("是否刪除该取用水户?", operation);
    }
};

/**
 * 查询取水许可证列表
 */
WrWpcM.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    WrWpcM.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = WrWpcM.initColumn();
    var table = new BSTable(WrWpcM.id, "/wrWpcM/list", defaultColunms);
    table.setPaginationType("server");
    WrWpcM.table = table.init();
});

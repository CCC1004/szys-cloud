/**
 * 取用水户管理管理初始化
 */
var WrWiuB = {
    id: "WrWiuBTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WrWiuB.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            //编码若需隐藏，visible设置为 false
            {title: '编码', field: 'uuid', visible: false, align: 'center', valign: 'middle'},
            // {title: '登记序号', field: 'djxh', visible: true, align: 'center', valign: 'middle'},
            {title: '纳税人识别号', field: 'nsrsbh', visible: true, align: 'center', valign: 'middle'},
            {title: '取用水户全称', field: 'wiuNm', visible: true, align: 'center', valign: 'middle'},
            {title: '责任人姓名', field: 'lrNm', visible: true, align: 'center', valign: 'middle'},
            {title: '取用水户性质', field: 'lawAtt', visible: true, align: 'center', valign: 'middle'},
            {title: '取用水户类型', field: 'userTp', visible: true, align: 'center', valign: 'middle'},
            {title: '行业类别', field: 'tradTp', visible: true, align: 'center', valign: 'middle'},
            // {title: '是否为高耗水行业', field: 'isHiLev', visible: true, align: 'center', valign: 'middle'},
            // {title: '经济类型', field: 'ecoTp', visible: true, align: 'center', valign: 'middle'},
            // {title: '监控级别', field: 'monG', visible: true, align: 'center', valign: 'middle'},
            // {title: '取用类别', field: 'wiuTp', visible: true, align: 'center', valign: 'middle'},
            {title: '取水规模(万m3)', field: 'yrWu', visible: true, align: 'center', valign: 'middle'},
            {title: '办证状态', field: 'certSta', visible: true, align: 'center', valign: 'middle'},
            {title: '是否重点监控取用水户', field: 'isVimp', visible: false, align: 'center', valign: 'middle'},
            // {title: '归属行政层级代码', field: 'adlCd', visible: true, align: 'center', valign: 'middle'},
            {title: '归属行政层级', field: 'adlCdName', visible: true, align: 'center', valign: 'middle'},
            {title: '取水所在属地水利机关', field: 'sljgCd', visible: false, align: 'center', valign: 'middle'},
            {title: '取水所在属地税务机关', field: 'swjgCd', visible: false, align: 'center', valign: 'middle'},
            // {title: '网址', field: 'web', visible: true, align: 'center', valign: 'middle'},
            // {title: '邮箱', field: 'eMail', visible: true, align: 'center', valign: 'middle'},
            // {title: '地址', field: 'addr', visible: true, align: 'center', valign: 'middle'},
            // {title: '邮政编码', field: 'zip', visible: true, align: 'center', valign: 'middle'},
            // {title: '办公室电话', field: 'tel', visible: true, align: 'center', valign: 'middle'},
            // {title: '传真号码', field: 'fax', visible: true, align: 'center', valign: 'middle'},
            {title: '在用状态', field: 'isuse', visible: false, align: 'center', valign: 'middle'},
            {title: '营业状态', field: 'runSta', visible: true, align: 'center', valign: 'middle'},
            // {title: '时间戳', field: 'ts', visible: true, align: 'center', valign: 'middle'},
            // {title: '经度', field: 'lgtd', visible: true, align: 'center', valign: 'middle'},
            // {title: '纬度', field: 'lttd', visible: true, align: 'center', valign: 'middle'},
            // {title: '备注', field: 'nt', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
WrWiuB.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WrWiuB.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加取用水户管理
 */
WrWiuB.openAddWrWiuB = function () {
    var index = layer.open({
        type: 2,
        title: '添加取用水户管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wrWiuB/wrWiuB_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看取用水户管理详情
 */
WrWiuB.openWrWiuBDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '取用水户管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrWiuB/wrWiuB_update/' + WrWiuB.seItem.uuid
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看取用水户管理详情
 */
WrWiuB.openWrWiuBDetail2 = function () {
    WrWiuB.seItem = '069da8bf536644b2bddb97aba735b107';
        var index = layer.open({
            type: 2,
            title: '取用水户管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wrWiuB/wrWiuB_update/'+WrWiuB.seItem
        });
        this.layerIndex = index;
};

/**
 * 删除取用水户管理
 */
WrWiuB.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/wrWiuB/delete", function (data) {
                Feng.success("删除成功!");
                WrWiuB.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("wrWiuBId",WrWiuB.seItem.uuid);
            ajax.start();
        };

        Feng.confirm("是否刪除该取用水户?", operation);
    }
};

/**
 * 查询取用水户管理列表
 */
WrWiuB.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    WrWiuB.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = WrWiuB.initColumn();
    var table = new BSTable(WrWiuB.id, "/wrWiuB/list", defaultColunms);
    table.setPaginationType("client");
    WrWiuB.table = table.init();
});

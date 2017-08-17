define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'template'], function ($, undefined, Backend, Table, Form, Template) {

    var Controller = {
    	index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: 'json',
                    add_url: 'add',
                    edit_url: 'edit',
                    del_url: '',
                    multi_url: 'multi',
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                sortName: 'id',
                escape:false, 
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: __('主键'), operate: false},
                        {field: 'icon', title: __('图标'), formatter: Controller.api.formatter.icon},
                        {field: 'name', title: __('名称'), align: 'left', operate: 'LIKE {0}', placeholder: '真实姓名，模糊搜索'},
                        {field: 'rule', title: __('规则'), formatter: Controller.api.formatter.url, operate: false},
                        {field: 'type', title: __('类型'), formatter: Controller.api.formatter.type, style: 'min-width:100px;', operate: false},
                        {field: 'disable', title: __('是否有效'), formatter: Table.api.formatter.disable, style: 'min-width:100px;'},
                        {field: 'id', title: '<a href="javascript:;" class="btn btn-success btn-xs btn-toggle"><i class="fa fa-chevron-up"></i></a>', formatter: Controller.api.formatter.subnode, operate: false},
                        {field: 'operate', title: __('Operate'), events: Table.api.events.operate, formatter: function (value, row, index) {
        	                	return Table.api.formatter.operate(value, row, index, table);
        	            	}
                        }
                    ]
                ],
                //不分页
                pagination: false,
                //默认搜索
                search: false,
                //启用普通表单搜索
                commonSearch: false,
                //可以控制是否默认显示搜索单表,false则隐藏,默认为false
                searchFormVisible: false,
                //分页大小，不分页时此参数无效
                pageSize: 10,
                //为空则不显示标题，不定义默认显示：普通搜索
                titleForm: '',
            });

            // 为表格绑定事件
            Table.api.bindevent(table);
            
            //默认隐藏所有子节点
            table.on('post-body.bs.table', function (e, settings, json, xhr) {
                //$("a.btn[data-id][data-pid][data-pid!=0]").closest("tr").hide();
                $(".btn-node-sub.disabled").closest("tr").hide();

                //显示隐藏子节点
                $(".btn-node-sub").off("click").on("click", function (e) {
                    var status = $(this).data("shown") ? true : false;
                    $("a.btn[data-pid='" + $(this).data("id") + "']").each(function () {
                        $(this).closest("tr").toggle(!status);
                    });
                    $(this).data("shown", !status);
                    return false;
                });

            });
            //展开隐藏一级
            $(document.body).on("click", ".btn-toggle", function (e) {
                $("a.btn[data-id][data-pid][data-pid!=0].disabled").closest("tr").hide();
                var that = this;
                var show = $("i", that).hasClass("fa-chevron-down");
                $("i", that).toggleClass("fa-chevron-down", !show);
                $("i", that).toggleClass("fa-chevron-up", show);
                $("a.btn[data-id][data-pid][data-pid!=0]").not('.disabled').closest("tr").toggle(show);
                $(".btn-node-sub[data-pid=0]").data("shown", show);
            });
            //展开隐藏全部
            $(document.body).on("click", ".btn-toggle-all", function (e) {
                var that = this;
                var show = $("i", that).hasClass("fa-plus");
                $("i", that).toggleClass("fa-plus", !show);
                $("i", that).toggleClass("fa-minus", show);
                $(".btn-node-sub.disabled").closest("tr").toggle(show);
                $(".btn-node-sub").data("shown", show);
            });
        },
        add: function () {
            Controller.api.bindevent();
        },
        edit: function () {
            Controller.api.bindevent();
        },
        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"));
            },
            formatter: {//渲染的方法
            	icon: function (value, row, index) {
                    return '<i class="' + value + '"></i>';
                },
	            url: function (value, row, index) {
	            	if (value == '' || value == null) {
	            		return '-';
	            	}
	                return '<div class="input-group input-group-sm" style="width:100%;"><input type="text" class="form-control input-sm" value="' + value + '"><span class="input-group-btn input-group-sm"><a href="' + value + '" target="_blank" class="btn btn-default btn-sm"><i class="fa fa-link"></i></a></span></div>';
	            },
	            type: function (value, row, index) {
                	//颜色状态数组,可使用red/yellow/aqua/blue/navy/teal/olive/lime/fuchsia/purple/maroon
                    var colorArr = {"1": 'red', "2": 'yellow', "3": 'aqua'};
                    var valueArr = {"1": '主模块', "2": '子功能', "3": '子权限'};
                    var classArr = {"1": 'fa fa-bookmark', "2": 'fa fa-bookmark-o', "3": 'fa fa-circle-o'};
                    //转为字符串
                    value = value + "";
                    //渲染状态
                    var html = '<span class="text-' + colorArr[value] + '"><i class="' + __(classArr[value]) + '"></i> ' + __(valueArr[value]) + '</span>';
                    return html;
                },
                subnode: function (value, row, index) {
                    return '<a href="javascript:;" data-id="' + row['id'] + '" data-pid="' + row['pid'] + '" class="btn btn-xs '
                            + (row['haschild'] == '1' ? 'btn-success' : 'btn-default disabled') + ' btn-node-sub"><i class="fa fa-sitemap"></i></a>';
                },
            },
            bindevent: function () {
                var iconlist = [];
                Form.api.bindevent($("form[role=form]"));
                $(document).on('click', ".btn-search-icon", function () {
                    if (iconlist.length == 0) {
                        $.get(ctxUrl + "/assets/libs/font-awesome/less/variables.less", function (ret) {
                            var exp = /fa-var-(.*):/ig;
                            var result;
                            while ((result = exp.exec(ret)) != null) {
                                iconlist.push(result[1]);
                            }
                            Layer.open({
                                type: 1,
                                area: ['460px', '300px'], //宽高
                                content: Template('chooseicontpl', {iconlist: iconlist})
                            });
                        });
                    } else {
                        Layer.open({
                            type: 1,
                            area: ['460px', '300px'], //宽高
                            content: Template('chooseicontpl', {iconlist: iconlist})
                        });
                    }
                });
                $(document).on('click', '#chooseicon ul li', function () {
                    $("input[name='icon']").val('fa fa-' + $(this).data("font"));
                    Layer.closeAll();
                });
                $(document).on('keyup', 'input.js-icon-search', function () {
                    $("#chooseicon ul li").show();
                    if ($(this).val() != '') {
                        $("#chooseicon ul li:not([data-font*='" + $(this).val() + "'])").hide();
                    }
                });
            }
        }
    };
    return Controller;
});
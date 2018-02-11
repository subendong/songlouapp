/**
 * jQuery分页插件
 * 原创作者：苏本东
 * 时间：2012-07-03 15:40:29
 */
jQuery.Pagination =
{
   ShowPage: function(total_number, page_size, page_index, page_list, show_total_page, show_page_index, show_input, show_page_size) {
       var total_page = total_number % page_size > 0 ? parseInt(total_number / page_size) + 1 : total_number / page_size; //总页数
       var page_list_cut_one = page_list - 1;
       var page_list_total_page = total_page % page_list > 0 ? parseInt(total_page / page_list) + 1 : total_page / page_list;
       var page_index_list_total_page = page_index % page_list > 0 ? parseInt(page_index / page_list) + 1 : page_index / page_list;
       var startI = 0;
       var endI = 0;
       var a = 0;
       page_index = page_index > total_page ? total_page : page_index;
       page_index = page_index < 1 ? 1 : page_index;

       //获取链接链里面的开始页和结束页
       switch (page_list_total_page) {
           case 0:
               startI = 1;
               endI = 1;
               break;
           case 1:
               startI = 1;
               endI = total_page;
               break;
           default:
               a = parseInt((page_index - 1) / page_list);
               startI = a * page_list + 1;
               endI = (a + 1) * page_list;
               if (page_index_list_total_page == page_list_total_page) {
                   startI = total_page - page_list_cut_one;
                   endI = total_page;
               }
               break;
       }
       
       var arr = new Array();
       
       //处理首页、上一页
       if (page_index == 1) {
           arr.push("<span title=\"首页\" class=\"grey\" page_index=\"1\">首页</span>");
           arr.push("<span title=\"上一页\" class=\"grey\" page_index=\"1\">上一页</span>");
       }
       else {
           arr.push("<span title=\"首页\" class=\"grey\" page_index=\"1\">首页</span>");
           arr.push("<span title=\"上一页\" class=\"grey\" page_index=\"" + (parseInt(page_index) - 1) + "\">上一页</span>");
       }

       //处理链接链

       for (var x = startI; x <= endI; x++) {
           if (x == page_index) {
               arr.push("<span class=\"light\" page_index=\"" + x + "\">" + x + "</span>");
           }
           else {
               arr.push("<span class=\"grey\" page_index=\"" + x + "\">" + x + "</span>");
           }
       }

       //处理下一页、尾页
       if (page_index == total_page || total_page == 0) {
           arr.push("<span class=\"grey\" page_index=\"" + page_index + "\">下一页</span>");
           arr.push("<span class=\"grey\" page_index=\"" + page_index + "\">尾页</span>");
       }
       else {x
           arr.push("<span title=\"下一页\" class=\"grey\" page_index=\"" + (parseInt(page_index) + 1) + "\">下一页</span>");
           arr.push("<span title=\"尾页\" class=\"grey\" page_index=\"" + total_page + "\">尾页</span>");
       }

       //是否显示记录数和当前页数
       //push.push("<i style=\"margin-left:20px;\">");
       if (show_total_page) {
           arr.push("共" + total_number + "条 ");
       }

       if (show_page_index) {
           arr.push("当前第" + page_index + "/" + total_page + "页 ");
       }

       if (show_page_size) {
           arr.push(page_size + "条/页 ");
       }
       //push.push("</i>");

       if (show_input) {
           arr.push("<input type=\"text\" name=\"page_index\" id=\"page_index\" value=\"" + page_index + "\" size=\"3\" />&nbsp;");
           arr.push("<input type=\"button\" value=\"查询\" onclick=\"go()\" id=\"pagination_submit\" />\n");
           arr.push("<script language=\"javascript\">\n");
           arr.push("function go()\n");
           arr.push("{\n");
           arr.push("var page_index = document.getElementById(\"page_index\").value;\n");
           arr.push("page_index = isNaN(page_index) || page_index == \"\" ? 1 : page_index;\n");
           arr.push("setPage(page_index);\n");
           arr.push("}\n");
           arr.push("<\/script>\n");//注意这里，我加了个反斜杠，用来转义的，如果单独放在JS文件里面，就没必要加反斜杠了。
       }

       return arr.join("");
   }
}
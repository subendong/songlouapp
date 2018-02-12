# songlouapp
1.for study
2.在网上下的一套模板后台，iframe子页面关闭父页面有点问题，要这么写：parent.window.$('.layui-layer-close').trigger('click')
3.submit提交的时候，要在form的submit事件里面写额外的代码，并且能够使用模板自带的validator验证。验证完之后，可以继续执行自定义的form的submit事件，不能在button的click事件处理。我记得之前用type为submit的input处理时，click事件是可以阻止form的submit事件的。但是不知道为什么类型为submit的button的click事件，不能完全阻止form的submit事件。
4.复选框美化用了icheck
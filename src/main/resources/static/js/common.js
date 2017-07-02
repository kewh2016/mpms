function pageNumHtmlInit(pageCount, pageNo) {
	var html = "";
	if (pageCount <= 6) {
		for (var i = 0; i < pageCount; i++) {
			html += '<span class="page-num fl" pageno="' + (i + 1) + '">'
					+ (i + 1) + '</span>';
		}
	} else if (pageNo <= 4) {
		for (var i = 0; i < 5; i++) {
			html += '<span class="page-num fl" pageno="' + (i + 1) + '">'
					+ (i + 1) + '</span>';
		}
		html += '<span class="ellipsis fl">...</span><span class="page-num fl" pageno="'
				+ (i + 1) + '">' + pageCount + '</span>'
	} else if (pageNo < pageCount - 3) {
		html = '<span class="page-num fl">1</span><span class="ellipsis fl">...</span>';
		for (var i = pageNo - 1; i <= pageNo - 0 + 1; i++) {
			html += '<span class="page-num fl" pageno="' + i + '">' + i
					+ '</span>';
		}
		html += '<span class="ellipsis fl">...</span><span class="page-num fl">'
				+ pageCount + '</span>';
	} else if (pageNo >= pageCount - 3) {
		html = '<span class="page-num fl">1</span><span class="ellipsis fl">...</span>';
		for (var i = 0; i < 5; i++) {
			var pn = pageCount - 4 + i;
			html += '<span class="page-num fl" pageno="' + pn + '">' + pn
					+ '</span>';
		}
	}
	return html;
}

function post(URL, PARAMS) {
	var temp = document.createElement("form");
	temp.action = URL;
	temp.method = "post";
	temp.style.display = "none";
	for ( var x in PARAMS) {
		var opt = document.createElement("textarea");
		opt.name = x;
		opt.value = PARAMS[x];
		// alert(opt.name)
		temp.appendChild(opt);
	}
	document.body.appendChild(temp);
	temp.submit();
	return temp;
}
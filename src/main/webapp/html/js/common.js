// 获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
}

// 获取本地 URL
function getLocalURL() {
	if ( location.href.substring(0, 4) == "file" ) {
		// 在客户端内
		return "boohee://web/" + location.href.substring(0, location.href.lastIndexOf('/')) + "/";
	} else {
		// 在浏览器内
		return location.href.substring(0, location.href.lastIndexOf('/')) + "/";
	}
}
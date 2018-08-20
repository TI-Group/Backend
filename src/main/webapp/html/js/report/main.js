var host = 'http://bingo.boohee.com/api/v1/health/analyses';
var ifDebug = (getUrlParam('debug') == 'true');
var token = getUrlParam('token');
var ifFromQuiz = (getUrlParam('quiz') == 'true');
var targetScore;
var app_version = getUrlParam('app_version');
var app_device = getUrlParam('app_device');
if ( ifDebug ) host = 'http://bingo.iboohee.cn/api/v1/health/analyses';
$.getJSON(host + "?token=" + token+"&app_version="+app_version+"&app_device="+app_device).done(function (data) {
	// 分数
	targetScore = data.score;
	countUp();

	// 健康评级
	if ( parseInt(targetScore) >= 96 ) {
		$("#healthGradeS").addClass("selected");
	} else if ( parseInt(targetScore) >= 86 ) {
		$("#healthGradeA").addClass("selected");
	} else if ( parseInt(targetScore) >= 76 ) {
		$("#healthGradeB").addClass("selected");
	} else if ( parseInt(targetScore) >= 61 ) {
		$("#healthGradeC").addClass("selected");
	} else {
		$("#healthGradeF").addClass("selected");
	}

	// BMI
	$("#bmiTitle").append('<div class="bigData">' + data.bmi.value + '<sup>（' + data.bmi.title + '）</sup></div>');
	var bmiPercent = Math.round(((parseFloat(data.bmi.value) - 15) / (35 - 15))*100);
	if ( bmiPercent > 100 ) bmiPercent = 100;
	if ( bmiPercent < 0 ) bmiPercent = 0;
	$("#bmiProgress").html('<div style="width:' + bmiPercent + '%"></div><span style="left:' + bmiPercent + '%">' + data.bmi.value + '</span>');

	// 体脂率
	$("#bodyFatTitle").append('<div class="bigData">' + data.fat.value + '<sup>%（' + data.fat.title + '）</sup></div>');
	var bodyFatPercent = Math.round(((data.fat.value - 3) / (40 - 3))*100);
	if ( bodyFatPercent > 100 ) bodyFatPercent = 100;
	if ( bodyFatPercent < 0 ) bodyFatPercent = 0;
	$("#bodyFatProgress")
	.html('<div style="width:' + bodyFatPercent + '%"></div><span style="left:' + bodyFatPercent + '%">' + data.fat.value + '%</span>')
	.addClass(data.sex);

	var bodyConclusionString = "综合来看，你的身材属于";
	if ( window.screen.width > 320 ) bodyConclusionString = "综合 BMI 和体脂率，你的身材属于";
	$("#bodyConclusion").html('<div>' + bodyConclusionString + '<span>' + data.evaluation + '型</span></div>');

	// 最佳体重、健康体重范围
	$("#weightInfoContainer").html('<div><h4>健康体重范围</h4><h5>' + data.health_weight_range + '<sup>公斤</sup></h5></div>');
	if( data.health_weight_range == null ) {
		$("#weightInfoContainer").hide();
		$("#exclusion").removeClass("borderTop");
	}

	// 身体年龄
	$("#ageTitle").append('<div class="bigData">' + data.young.bodyage.toFixed(0) + '<sup>岁</sup></div>');
	var agePercent = Math.round(((parseInt(data.young.bodyage) - 10) / (60 - 10))*100);
	if ( agePercent > 100 ) agePercent = 100;
	if ( agePercent < 0 ) agePercent = 0;
	$("#ageProgress").html('<div style="width:' + agePercent + '%"></div><span style="left:' + agePercent + '%">' + parseInt(data.young.bodyage) + '</span>');
	if ( data.young.age > data.young.bodyage ) {
		$("#ageConclusion span").html(data.young.age - parseInt(data.young.bodyage) + " 岁");
	} else {
		$("#ageConclusion").hide();
	}

	// 预算热量
	$("#calorieTitle").append('<div class="bigData">' + data.calory.target + '<sup>千卡</sup></div>');
	$("#targetRangeValue").html( data.calory.target_range );

	// 燃脂心率
	$("#heartRateTitle").append('<div class="bigData">' + data.fat_burning_rate + '<sup>次/分钟</sup></div>');
	

	// 脚部
	if ( ifFromQuiz ) {
		$("#startButton").addClass("bigButton");
		$("#quizButton").addClass("smallButton");
	} else {
		$("#startButton").hide();
		$("#quizButton").addClass("bigButton");
	}
});
$(".tipHandler").click(function(){
	$("#tipBox p").html( $(this).attr("rel") );
	$("#tipBox").fadeIn();
	return false;
});

$("#tipBox a").click(function(){
	$("#tipBox").fadeOut();
	return false;
});

function countUp() {
	var preview = parseInt( $("#scoreValue").text() ) + 7;
	if ( preview >= targetScore ) {
		$("#scoreValue").html( targetScore );
		$("#scoreContainer").removeClass("circlePercent0").addClass("circlePercent" + Math.round(targetScore / 5));
		return;
	} else {
		$("#scoreValue").html( preview );
		setTimeout("countUp()", 50);
	}
}

// 获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
}
var host = 'https://food.boohee.com/fb/v1/foods/';
var foodCode = getUrlParam('code');
var titles = new Array(
	"热量",
	"蛋白质",
	"脂肪",
	"碳水化合物",
	"膳食纤维",
	"维生素A",
	"维生素C",
	"维生素E",
	"胡萝卜素",
	"维生素B1",
	"维生素B2",
	"烟酸",
	"胆固醇",
	"镁",
	"钙",
	"铁",
	"锌",
	"铜",
	"锰",
	"钾",
	"磷",
	"钠",
	"硒"
);
var units = new Array(
	"千卡",
	"克",
	"克",
	"克",
	"克",
	"微克",
	"毫克",
	"毫克",
	"微克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"毫克",
	"微克"
);

$.getJSON(host + foodCode + ".json").done(function (data) {
	var i = 0;
	$.each(data.ingredient, function(key, val) {
		var str =  '<tr>';
		var value = '--';
		if (data.ingredient[key]) {
			value = data.ingredient[key] + " " + units[i];
		}
		if (i == 0) {
			value = Math.round(data.ingredient[key]) + " " + units[i];
		}
		str += '<td class="left">' + titles[i] + '</td>';
		str += '<td class="center">' + value + '</td>';
		str += '<td class="right valueDesc" id=' + key + '></td>';
		str += '</tr>';
		$("#nutritionTable").append(str);
		i++;
	});

	$("#calory").html(data.lights.calory);
	$("#protein").html(data.lights.protein);
	$("#carbohydrate").html(data.lights.carbohydrate);
	$("#fat").html(data.lights.fat);
	$("#fiber_dietary").html(data.lights.fiber_dietary);
});
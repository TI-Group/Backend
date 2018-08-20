var host = 'https://food.boohee.com/fb/v1/foods/';
var foodCode = getUrlParam('code');
var foodDataString = getUrlParam('data');
var foodDataJson;
var foodKCal = 0;
var foodKJ = 0;
var foodUnit = "千卡";
var foodProtein = 0;
var foodFat = 0;
var foodCarbo = 0;
var foodFactor = 1;
var weightUnit = "克";

if ( foodDataString ) {
	foodDataJson = JSON.parse(decodeURIComponent(escape(foodDataString)));
	foodCode = foodDataJson.code;
	init( foodDataJson );
} else if ( foodCode ) {
	$.getJSON(host + foodCode + ".json").done(function (data) {
		init(data);
	});
}

function init(data) {
    if(window.androidI){
        androidI.passParam(JSON.stringify(data));
    }
	foodKCal = data.calory;
	foodKJ = Math.round(data.calory * 4.182);
	foodProtein = data.protein;
	foodFat = data.fat;
	foodCarbo = data.carbohydrate;
	if (data.is_liquid) weightUnit = "毫升";

	// 头部
	$("#foodImage").attr("src", data.thumb_image_url);
	$("#top a").attr("href", "boohee://show_image/" + encodeURIComponent(data.large_image_url));

	$("#foodName").html(data.name);

	if (data.health_light == 1) {
		$("#foodLight").html("<span class='greenLight'>绿灯食物</span> 推荐食用");
	} else if (data.health_light == 2) {
		$("#foodLight").html("<span class='yellowLight'>黄灯食物</span> 适量食用");
	} else if (data.health_light == 3) {
		$("#foodLight").html("<span class='redLight'>红灯食物</span> 少量食用");
	}

	// 热量
	$("#calorieValue").html(foodKCal);
	$("#calorieDesc").html('每100' + weightUnit +'（可食用部分）');

	// 切换热量单位
	$("#unitKJ").click(function(){
		foodUnit = "千焦";
		$(this).addClass("unitSelected");
		$("#unitKCal").removeClass("unitSelected");
		$("#calorieValue").html(foodKJ);
		$("#calorieUnit").html(foodUnit);
		refresh();
		return false;
	});
	$("#unitKCal").click(function(){
		foodUnit = "千卡";
		$(this).addClass("unitSelected");
		$("#unitKJ").removeClass("unitSelected");
		$("#calorieValue").html(foodKCal);
		$("#calorieUnit").html(foodUnit);
		refresh();
		return false;
	});

	// 榜单
	rankingList(data.id);

	// 营养素
	if (data.lights.calory) $("#tagContainer").append("<span>" + data.lights.calory + "</span>");
	if (data.lights.protein) $("#tagContainer").append("<span>" + data.lights.protein + "</span>");
	if (data.lights.fat) $("#tagContainer").append("<span>" + data.lights.fat + "</span>");
	if (data.lights.carbohydrate) $("#tagContainer").append("<span>" + data.lights.carbohydrate + "</span>");
	if (data.lights.gi) $("#tagContainer").append("<span>" + data.lights.gi + "</span>");
	if (data.lights.gl) $("#tagContainer").append("<span>" + data.lights.gl + "</span>");
	if ( data.lights.calory || data.lights.protein || data.lights.fat || data.lights.carbohydrate || data.lights.gi || data.lights.gl) $("#rankingList").show();

	var proteinCalorie = foodProtein * 4;
	var fatCalorie = foodFat * 9;
	var carboCalorie = foodCarbo * 4;
	var totalCalorie = proteinCalorie + fatCalorie + carboCalorie;
	var proteinPercent = Math.round(100 * proteinCalorie / totalCalorie);
	var fatPercent = Math.round(100 * fatCalorie / totalCalorie);
	var carboPercent = 100 - proteinPercent - fatPercent;
	if ( totalCalorie == 0 ) proteinPercent = fatPercent = carboPercent = 0; // 修复热量为零的bug

	$("#proteinPercent").html(proteinPercent + "%");
	$("#fatPercent").html(fatPercent + "%");
	$("#carboPercent").html(carboPercent + "%");

	$("#proteinCircle").addClass("circlePercent" + Math.round(proteinPercent / 5)).html(proteinPercent + "%");
	$("#fatCircle").addClass("circlePercent" + Math.round(fatPercent / 5)).html(fatPercent + "%");
	$("#carboCircle").addClass("circlePercent" + Math.round(carboPercent / 5)).html(carboPercent + "%");

	refresh();

	// 个性化单位
	$("#unitHandler").html('单位：每 100 ' + weightUnit);
	if (data.units.length) {
		for (var i = 0; i < data.units.length; i++) {
			// 单位重量
			weightString = '<tr>';
			if ( i > 2 ) { // 只显示3个
				weightString = '<tr class="moreWeightLine">';
			}
			weightString += '<td class="left">1' + data.units[i].unit;
			weightString += '<span>' + Math.round(data.units[i].weight) + ' ' + weightUnit + '，可食用部分：' +  Math.round(data.units[i].eat_weight) + ' ' + weightUnit + '</span></td>';
			weightString += '<td class="right weightItem" factor="';
			weightString += data.units[i].eat_weight / 100;
			weightString += '">' + Math.round(data.units[i].eat_weight * foodKCal / 100) + ' ' + foodUnit+ '</td>';
			weightString += '</tr>';
			$("#weightList").append(weightString);
		}

		if ( data.units.length > 0 ) {
			$("#foodWeightUnit").show();
		}

		if ( data.units.length > 3 ) { // 有4个或更多的时候才显示，正好3个的话不显示
			$("#weightList").after('<a href="#" id="weightSwitchHandler" class="closed"><span class="arrowDown">展开</span></a>');
			$("#weightSwitchHandler").click(function(){
				if ( $(this).hasClass("closed") ) {
					$(".moreWeightLine").fadeIn();
					$(this).removeClass("closed").html('<span class="arrowUp">收起</span>');
				} else {
					$(".moreWeightLine").fadeOut();
					$(this).addClass("closed").html('<span class="arrowDown">展开</span>');
				}
				return false;
			});
		}
	}

	$("#nutritionLink").attr("href", getLocalURL() + "food_nutrition.html?code=" + foodCode);

	// 原料与做法
	if (data.recipe_type == "douguo" || data.recipe_type == "own") {
		$("#recipeHandler").show();
		$("#recipeHandler a").attr("href", getLocalURL() + "food_recipe.html?code=" + foodCode + "&type=" + data.recipe_type);
	}

	// 血糖
	if (data.gi) {
		$("#bloodSugar").show();
		$("#giValue").html(data.gi);
		$("#giDesc").html(data.lights.gi);
		$("#glValue").html(data.gl);
		$("#glDesc").html(data.lights.gl);
	}

	// 评价
	if ( data.appraise ) {
		if ( data.appraise.substr(1, 3) !== "灯食物" ) {
			// 如果只是红绿灯的名词解释，那就没必要显示了
			$("#description").show().append('<p>' + data.appraise + '</p>');
		}
	}
}

// 同类对比
$.getJSON(host + foodCode + "/group_foods.json").done(function (group_data) {
	if ( !group_data.calory_rank ) return false;

	$("#calorieCompare").show();
	$("#compareConclusion").html("热量低于 <span>" + (100 - group_data.calory_rank) + "%</span> 的" + group_data.name);
	$("#comparePointer span").css("left", group_data.calory_rank + "%");
});

// 更新数据营养元素里的数据
function refresh() {
	$("#proteinValue").html( (foodProtein * foodFactor).toFixed(1) );
	$("#fatValue").html( (foodFat * foodFactor).toFixed(1) );
	$("#carboValue").html( (foodCarbo * foodFactor).toFixed(1) );

	if ( foodUnit == "千焦" ) {
		$(".weightItem").each(function(){
			$(this).html( Math.round(foodKJ * $(this).attr("factor")) + ' ' + foodUnit );
		});
	} else {
		$(".weightItem").each(function(){
			$(this).html( Math.round(foodKCal * $(this).attr("factor")) + ' ' + foodUnit );
		});
	}
}

// 食物榜单
function rankingList(id) {
	$.getJSON(host + id + "/rankings.json").done(function (ranking_data) {
		if (!ranking_data.records.length) return false;

		$("#rankingList").show();
		for (var i = 0; i < ranking_data.records.length; i++) {
			var rankingString = '<li><a href="' + getLocalURL() + 'food_ranking_list.html?id=' + ranking_data.records[i].id + '">';
			if (ranking_data.records[i].ranking) {
				rankingString += '「' + ranking_data.records[i].title + '」第 ';
				rankingString += ranking_data.records[i].ranking + ' 名';
			} else {
				rankingString += '收录在「' + ranking_data.records[i].title + '」';
			}
			rankingString += '</a></li>';
			$("#rankingList ul").append(rankingString);
		}
	});
}
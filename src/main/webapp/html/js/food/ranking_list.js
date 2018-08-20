var host = 'https://food.boohee.com/fb/v1/';
var rankingId = getUrlParam('id');
var unitList = {
	calory: {name: "热量", unit: "千卡"},
	protein: {name: "蛋白质", unit: "克"},
	fat: {name: "脂肪", unit: "克"},
	saturated_fat: {name: "饱和脂肪(酸)", unit: "克"},
	ufa: {name: "不饱和脂肪酸", unit: "克"},
	mufa: {name: "单不饱和脂肪酸", unit: "克"},
	pufa: {name: "多不饱和脂肪酸", unit: "克"},
	n3fa: {name: "n-3脂肪酸", unit: "克"},
	fatty_acid: {name: "反式脂肪(酸)", unit: "克"},
	cholesterol: {name: "胆固醇", unit: "毫克"},
	carbohydrate: {name: "碳水化合物", unit: "克"},
	sugar: {name: "糖", unit: "克"},
	fiber_dietary: {name: "膳食纤维", unit: "克"},
	natrium: {name: "钠", unit: "毫克"},
	vitamin_a: {name: "维生素A", unit: "μgRE"},
	carotene: {name: "胡萝卜素", unit: "微克"},
	vitamin_d: {name: "维生素D", unit: "微克"},
	vitamin_e: {name: "维生素E", unit: "mgRE"},
	vitamin_k: {name: "维生素K", unit: "微克"},
	thiamine: {name: "维生素B1", unit: "毫克"},
	lactoflavin: {name: "维生素B2", unit: "毫克"},
	vitamin_b6: {name: "维生素B6", unit: "毫克"},
	vitamin_b12: {name: "维生素B12", unit: "微克"},
	vitamin_c: {name: "维生素C", unit: "毫克"},
	niacin: {name: "烟酸", unit: "毫克"},
	folacin: {name: "叶酸", unit: "微克"},
	choline: {name: "胆碱", unit: "毫克"},
	pantothenic: {name: "泛酸", unit: "毫克"},
	biotin: {name: "生物素", unit: "微克"},
	phosphor: {name: "磷", unit: "毫克"},
	kalium: {name: "钾", unit: "毫克"},
	calcium: {name: "钙", unit: "毫克"},
	magnesium: {name: "镁", unit: "毫克"},
	iron: {name: "铁", unit: "毫克"},
	zinc: {name: "锌", unit: "毫克"},
	iodine: {name: "碘", unit: "微克"},
	selenium: {name: "硒", unit: "微克"},
	copper: {name: "铜", unit: "毫克"},
	manganese: {name: "锰", unit: "毫克"},
	fluorine: {name: "氟", unit: "毫克"},
	chrome: {name: "铬", unit: "微克"},
	dha: {name: "DHA", unit: "克"},
	proanthocyanidin: {name: "原花青素", unit: "毫克"},
	quercetin: {name: "槲皮素", unit: "毫克"},
	anthocyanin: {name: "花色苷", unit: "毫克"},
	soy_isoflavone: {name: "大豆异黄酮", unit: "毫克"},
	lutein: {name: "叶黄素", unit: "微克"},
	plant_alcohol: {name: "植物玆醇", unit: "毫克"},
	carnitine: {name: "肉碱", unit: "毫克"},
	alcohol: {name: "酒精度", unit: "%vol"},
	gi: {name: "GI", unit: ""},
	gl: {name: "GL", unit: ""},
	water: {name: "含水量", unit: "克"}
};


$.getJSON(host + "food_rankings/" + rankingId + ".json").done(function (data) {
	$("header").append('<h1>' + data.title + '</h1');
	if (data.description) {
		$("header").append('<p>' + data.description + '</p>');
	}

	for (var i = 0; i < data.foods.length; i++) {
		var str = '<li>';
		if (data['display_num?']) {
			str = '<li class="order">';
		}
		str += '<a href="' + getLocalURL() + 'food_detail.html?code=' + data.foods[i].code + '">';
		str += '<img src="' + data.foods[i].thumb_image_url + '">';
		str += '<h2>' + data.foods[i].name + '</h2>';
		if (data.show_column) {
			var prefix = unitList[data.show_column].name + ': ';
			var suffix = ' / 100克';
			if (data.show_column == "calory") prefix = '';
			if (data.show_column == "gi") suffix = '';
			str += '<p>' + prefix + '<span>' + data.foods[i][data.show_column] + '</span> ' + unitList[data.show_column].unit + suffix +  '</p>';
		}
		str += '</a>';
		str += '</li>';	
		$("#rankingList").append(str);
	}

});
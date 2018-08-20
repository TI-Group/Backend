var host = 'https://food.boohee.com/fb/v1/foods/';
var foodCode = getUrlParam('code');
var type = getUrlParam('type');

if ( type == "douguo" ) {
	$.getJSON(host + foodCode + "/recipe.json").done(function (data) {
		$("#douguoContainer").show();
		$("#banner").attr("src", data.image_url);
		$("#douguoContainer h1").html(data.name);

		// 原料
		if ( data.condiments.length ) {
			for (var i = 0; i < data.condiments.length; i++) {
				condimentString = '<tr>';
				condimentString += '<th>' + data.condiments[i].name + '</th>';
				if ( data.condiments[i].amount ) {
					condimentString += '<td>' + data.condiments[i].amount + '</td>';
				}
				condimentString += '<tr>';
				$("#douguoCondiment").append(condimentString);
			}
		}
		// 做法
		if ( data.steps.length ) {
			for (var i = 0; i < data.steps.length; i++) {
				stepString = '<tr>';
				stepString += '<td class="order">' + (i + 1) + '</td>';
				stepString += '<td>' + data.steps[i].desc + '</td>';
				stepString += '<td class="right">';
				if ( data.steps[i].image_url ) {
					stepString += '<img src="' + data.steps[i].image_url + '" />';
				}
				stepString += '</td>';
				stepString += '<tr>';
				$("#douguoStep").append(stepString);
			}
		}
		// 小贴士
		if ( data.tips ) {
			$("#douguoTip").append(data.tips.replace(/\n/g, "<br /><br />"));
		}
	});
}

if ( type == "own" ) {
	$.getJSON(host + foodCode + "/recipe/whole.json").done(function (data) {
		$("#ownContainer").show();

		// 主料
		if ( data.data.materials.major_materials ) {
			$("#ownContainer").append('<section><h2>主料</h2><table id="materialOne" class="twoColumn"></table></section>');
			for (var i = 0; i < data.data.materials.major_materials.length; i++) {
				materialString = '<tr>';
				materialString += '<th>' + data.data.materials.major_materials[i].name + '</th>';
				materialString += '<td>' + data.data.materials.major_materials[i].weight + ' 克</td>';
				materialString += '<tr>';
				$("#materialOne").append(materialString);
			}
		}

		// 辅料
		if ( data.data.materials.minor_materials ) {
			$("#ownContainer").append('<section><h2 class="borderTop">辅料</h2><table id="materialTwo" class="twoColumn"></table></section>');
			for (var i = 0; i < data.data.materials.minor_materials.length; i++) {
				materialString = '<tr>';
				materialString += '<th>' + data.data.materials.minor_materials[i].name + '</th>';
				materialString += '<td>' + data.data.materials.minor_materials[i].weight + ' 克</td>';
				materialString += '<tr>';
				$("#materialTwo").append(materialString);
			}
		}

		// 配料
		if ( data.data.materials.seasoning ) {
			$("#ownContainer").append('<section><h2 class="borderTop">配料</h2><table id="materialThree" class="twoColumn"></table></section>');
			for (var i = 0; i < data.data.materials.seasoning.length; i++) {
				materialString = '<tr>';
				materialString += '<th>' + data.data.materials.seasoning[i].name + '</th>';
				materialString += '<td>' + data.data.materials.seasoning[i].weight + ' 克</td>';
				materialString += '<tr>';
				$("#materialThree").append(materialString);
			}
		}

		// 原料
		if ( data.data.materials.raw ) {
			$("#ownContainer").append('<section><h2>原料</h2><table id="materialRaw" class="twoColumn"></table></section>');
			for (var i = 0; i < data.data.materials.raw.length; i++) {
				materialString = '<tr>';
				materialString += '<th>' + data.data.materials.raw[i].name + '</th>';
				materialString += '<td>' + Math.round(data.data.materials.raw[i].weight) + ' 克</td>';
				materialString += '<tr>';
				$("#materialRaw").append(materialString);
			}
		}

		// 做法
		if ( data.data.ext ) {
			$("#ownContainer").append('<section><h2 class="borderTop">做法</h2><div id="tutorial"></div></section>');
			$("#tutorial").append(data.data.ext.replace(/\n/g, "<br /><br />"));
		}
	});
}
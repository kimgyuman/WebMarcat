var homeStartNum = 13;
var profile1TabStartNum = 13;
var sellStatus = 'SELL';
var viewStatus = '';
var homeResult = true;
var profile1Result = true;
var homeTab = true;
var profile1Tab = false;
const home2 = document.getElementById('home2-tab');
var id = new URL(window.location.href).searchParams.get('id');


function message(){
    var url="/user/userLink/sendMessage?id=" + id;
    var option="width=500, height=500, top=200, left=450, scrollbars=no, resizable=no"
    window.open(url, "", option);
}

$('#home2-tab').on("click", function () {
    if (homeTab != true) {
        homeTab = true;
        profile1Tab = false;
    }
    console.log(homeTab);
    console.log(profile1Tab);
});
$('#profile1-tab').on('click', function () {
    if (profile1Tab != true) {
        profile1Tab = true;
        homeTab = false;
    }
    console.log(homeTab);
    console.log(profile1Tab);
});

$(window).scroll(function () {
    if (homeTab == true) {
        if ($(window).scrollTop() >= ($(document).height() - 700) && homeResult == true) {
            var searchType = {
                "startNum": homeStartNum,
                "viewStatus": viewStatus,
                "id": id
            }
            $.ajax({
                type: "POST",
                url: "/user/userLink/MarketActivityPaging",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(searchType),
                dataType: "json",
                async: false,
                success: function (data) {
                    console.log(data);
                    if ($.isEmptyObject(data) == false) {
                        var appendItems = "";
                        $.each(data, function (index, item) {
                            appendItems += '<tr>';
                            appendItems += '<td>' + item.goodsId + '</td>';
                            appendItems += '<td>' + item.title + '</td>';
                            appendItems += '<td>' + item.createTime + '</td>';
                            appendItems += '</tr>';
                        });
                        $(".MarketWrap").append(appendItems);
                        homeStartNum += 12;
                    } else {
                        homeResult = false;
                    }
                },
                error: function () {
                    alert("Error");
                }
            });
        }
    } else if (profile1Tab == true) {
        if ($(window).scrollTop() >= ($(document).height() - 700) && profile1Result == true) {
            var searchType = {
                "startNum": profile1TabStartNum,
                "viewStatus": viewStatus,
                "id": id
            }
            $.ajax({
                type: "POST",
                url: "/user/userLink/boardActivityPaging",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(searchType),
                dataType: "json",
                async: false,
                success: function (data) {
                    console.log(data);
                    if ($.isEmptyObject(data) == false) {
                        var appendItems = "";
                        $.each(data, function (index, item) {
                            appendItems += '<tr>';
                            appendItems += '<td>' + item.id + '</td>';
                            appendItems += '<td>' + item.title + '</td>';
                            appendItems += '<td>' + item.createTime + '</td>';
                            appendItems += '</tr>';
                        });
                        $(".boardWrap").append(appendItems);
                        profile1TabStartNum += 12;
                    } else {
                        profile1Result = false;
                    }
                },
                error: function () {
                    alert("Error");
                }
            });
        }
    }

});

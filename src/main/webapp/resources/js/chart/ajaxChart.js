window.onload = function () {

    //카테고리별 등록량 chart
    $.ajax({
        url:'/admin/categoriesChart',
        type:'get',
        success: function(data) {
            let s = JSON.stringify(data);
            let parse = JSON.parse(s);

            var labelList = [];
            var valueList = [];
            var categoriesChartTicks = 1;

            for (var i = 0; i < parse.length; i++) {
                var jdata = JSON.parse(parse[i]);
                labelList.push(jdata.name);
                valueList.push(jdata.cnt);
            }

            $.each(valueList, function (index,item) {
                if (item > 5000) {
                    categoriesChartTicks = 1000;
                }else if(item >500) {
                    categoriesChartTicks = 100;
                }else if(item >50) {
                    categoriesChartTicks = 10;
                }
            });

            var data2 = {
                labels: labelList,
                datasets: [{
                    label : '등록량',
                    data: valueList,
                    backgroundColor: 'rgba(153, 102, 255, 0.2)',
                    borderColor: 'rgba(153, 102, 255, 0.2)',
                    borderWidth: 1
                }],

            };

            const itx = document.getElementById("myChart2").getContext('2d');
            const cchart = new Chart(itx, {
                type: 'bar',
                data: data2,
                options: {
                    scales: {
                        y: {
                            ticks: {
                                stepSize: categoriesChartTicks,
                                min: 0
                            }
                        }
                    }
                }
            });
        },
        error: function(err) {
            alert("실패")
        }
    });


    //최근 1년간 회원수 chart
    $.ajax({
        url:'/admin/memberChart',
        type:'get',
        success: function(data) {
            var memberChartTicks = 1;

            if(data.week > 50) {
                memberChartTicks = 10;
            }else if(data.week > 250) {
                memberChartTicks = 50;
            }else if(data.week > 500) {
                memberChartTicks = 100;
            }

            const mtx = document.getElementById("myChart3").getContext('2d');

            const mchart = new Chart(mtx, {
                type: 'line',
                data: {
                    labels: ['4주전','3주전','2주전','1주전','현재'],
                    datasets: [{
                        label: '회원 수',
                        data: [
                            data.fourWeekAgo,
                            data.threeWeekAgo,
                            data.twoWeekAgo,
                            data.oneWeekAgo,
                            data.week,
                        ],
                        backgroundColor: 'rgba(153, 102, 255, 0.2)',
                        borderColor: 'rgba(153, 102, 255, 0.5)'
                    }]
                }, options: {
                    scales: {
                        y: {
                            ticks: {
                                stepSize: memberChartTicks,
                                min: 0
                            }
                        }
                    }
                }

            });
            
        },
        error: function(err) {
            alert("실패")
        }
    });
}


//지역별 카테고리 등록량
$("button").on("click", function() {

     let area = $(this).valueOf().text();

    $.ajax({
        url:'/admin/areaChart',
        type:'get',
        data:{'area':area},
        success: function(data) {
            let chartStatus = Chart.getChart("myChart4");
            if (chartStatus != undefined) {
                chartStatus.destroy();
                let a = JSON.stringify(data);
                let aparse = JSON.parse(a);

                var alabelList = [];
                var avalueList = [];

                for (var i = 0; i < aparse.length; i++) {
                    var adata = JSON.parse(aparse[i]);
                    alabelList.push(adata.cate);
                    avalueList.push(adata.cnt);
                }

                var adata2 = {
                    labels: alabelList,
                    datasets: [{
                        label : '등록량',
                        data: avalueList,
                        backgroundColor: 'rgba(153, 102, 255, 0.2)',
                        borderColor: 'rgba(153, 102, 255, 1)',
                        borderWidth: 1
                    }],

                };

                var atx = document.getElementById("myChart4");
                var achart = new Chart(atx, {
                    type: 'line',
                    data: adata2,
                    options: {
                        scales: {
                            y: {
                                ticks: {
                                    stepSize: 1,
                                    min: 0
                                }
                            }
                        }
                    }
                });
            }else {
                let a = JSON.stringify(data);
                let aparse = JSON.parse(a);

                var alabelList = [];
                var avalueList = [];

                for (var i = 0; i < aparse.length; i++) {
                    var adata = JSON.parse(aparse[i]);
                    alabelList.push(adata.cate);
                    avalueList.push(adata.cnt);
                }

                var adata2 = {
                    labels: alabelList,
                    datasets: [{
                        label : '등록량',
                        data: avalueList,
                        backgroundColor: 'rgba(153, 102, 255, 0.2)',
                        borderColor: 'rgba(153, 102, 255, 1)',
                        borderWidth: 1
                    }],

                };

                var atx = document.getElementById("myChart4");
                var achart = new Chart(atx, {
                    type: 'line',
                    data: adata2,
                    options: {
                        scales: {
                            y: {
                                ticks: {
                                    stepSize: 1,
                                    min: 0
                                }
                            }
                        }
                    }
                });
            }

        },
        error: function(err) {
            alert("실패")
        }
    });
});


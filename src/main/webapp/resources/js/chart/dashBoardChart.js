window.onload = function() {
    let fixToday = new Date();
    let today = new Date();
    let oneAgo = new Date(today.setDate(today.getDate() - 1));
    let twoAgo = new Date(today.setDate(today.getDate() - 1));
    let threeAgo = new Date(today.setDate(today.getDate() - 1));
    let fourAgo = new Date(today.setDate(today.getDate() - 1));
    let fiveAgo = new Date(today.setDate(today.getDate() - 1));
    let sixAgo = new Date(today.setDate(today.getDate() - 1));

    let labelList = [sixAgo.getDate() + '일', fiveAgo.getDate() + '일', fourAgo.getDate() + '일', threeAgo.getDate() + '일', twoAgo.getDate() + '일', oneAgo.getDate() + '일', fixToday.getDate() + '일'];

    const goodsCnt1 = document.getElementById("today").value;
    const goodsCnt2 = document.getElementById("oneAgo").value;
    const goodsCnt3 = document.getElementById("twoAgo").value;
    const goodsCnt4 = document.getElementById("threeAgo").value;
    const goodsCnt5 = document.getElementById("fourAgo").value;
    const goodsCnt6 = document.getElementById("fiveAgo").value;
    const goodsCnt7 = document.getElementById("sixAgo").value;

    const boardCnt1 = document.getElementById("bdToday").value;
    const boardCnt2 = document.getElementById("bdOneAgo").value;
    const boardCnt3 = document.getElementById("bdTwoAgo").value;
    const boardCnt4 = document.getElementById("bdThreeAgo").value;
    const boardCnt5 = document.getElementById("bdFourAgo").value;
    const boardCnt6 = document.getElementById("bdFiveAgo").value;
    const boardCnt7 = document.getElementById("bdSixAgo").value;


    var goodsTicks;
    if (goodsCnt1 > 300 || goodsCnt2 > 300 || goodsCnt3 > 300 || goodsCnt4 > 300 || goodsCnt5 > 300 || goodsCnt6 > 300 || goodsCnt7 > 300) {
        goodsTicks = 100;
    }else if (goodsCnt1 > 30 || goodsCnt2 > 30 || goodsCnt3 > 30 || goodsCnt4 > 30 || goodsCnt5 > 30 || goodsCnt6 > 30 || goodsCnt7 > 30) {
        goodsTicks = 10;
    }else {
        goodsTicks = 1;
    }

    const myChart = document.getElementById("myChart").getContext("2d");

    const lineChart = new Chart(myChart, {
        type: 'line',
        data: {
            labels: labelList,
            datasets: [{
                label: '상품 게시글 수',
                data: [
                    goodsCnt7,
                    goodsCnt6,
                    goodsCnt5,
                    goodsCnt4,
                    goodsCnt3,
                    goodsCnt2,
                    goodsCnt1
                ],
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 0.5)'
            }]
        }, options: {
            scales: {
                y: {
                    ticks: {
                        stepSize: goodsTicks,
                        min: 0
                    }
                }
            }
        }

    });

    var boardTicks;
    if (boardCnt1 > 300 || boardCnt2 > 300 || boardCnt3 > 300 || boardCnt4 > 300 || boardCnt5 > 300 || boardCnt6 > 300 || boardCnt7 > 300) {
        boardTicks = 100;
    }else if (boardCnt1 > 30 || boardCnt2 > 30 || boardCnt3 > 30 || boardCnt4 > 30 || boardCnt5 > 30 || boardCnt6 > 30 || boardCnt7 > 30) {
        boardTicks = 10;
    }else {
        boardTicks = 1;
    }


        const myChart1 = document.getElementById("myChart1").getContext("2d");

        const lineChart1 = new Chart(myChart1, {
            type: 'line',
            data: {
                labels: labelList,
                datasets: [{
                    label: '게시글 수',
                    data: [
                        boardCnt7,
                        boardCnt6,
                        boardCnt5,
                        boardCnt4,
                        boardCnt3,
                        boardCnt2,
                        boardCnt1
                    ],
                    backgroundColor: 'rgba(153, 102, 255, 0.2)',
                    borderColor: 'rgba(153, 102, 255, 0.5)'
                }]
            }, options: {
                scales: {
                    y: {
                        ticks: {
                            stepSize: boardTicks,
                            min: 0
                        }
                    }
                }
            }
        });
}

$(".goods-window").on("click",function() {

    window.open("/market",'market',"_blank")
});

$(".board-window").on("click",function() {

    window.open("/board",'market',"_blank")
});
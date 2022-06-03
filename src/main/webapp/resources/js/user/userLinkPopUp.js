const senderElement = document.getElementById('sendId');
const receiveElement = document.getElementById('recvId');
const messageElement = document.getElementById('message');

$(document).ready(function () {
        $('#sendByMessage').click(function () {
            var vali = validation();
            if(vali ===  false){
                e.preventDefault();
                console.log(vali)
                console.log(e)
                window.close;
            }

            var sendId = $('#sendId').val();
            var recvId = $('#recvId').val();
            var message = $('#message').val();

            var request = {"sendId": sendId, "recvId": recvId, "message": message};
            console.log(sendId);
            console.log(recvId);
            console.log(message);
            $.ajax({
                type: "POST",
                url: "/user/userLink/sendMessage",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(request),
                dataType: "json",
                async: true,
                success: function (data) {
                    console.log("data확인={}",data);
                    window.close();
                },
                error: function (e) {
                    console.log("에러발생",e)
                }
            });
        })
     });

function validation(){
    var send = senderElement.value;
    var receive = receiveElement.value;
    var message = messageElement.value;

    if(send === receive) {
        alert("상대방에게만 쪽지를 전달할 수 있습니다!");
        return false;
    }else if(message === ""){
        alert("메세지를 입력하세요!");
        return false;
    }else{
        alert("쪽지를 전송하였습니다!");
        return true;
    }
}
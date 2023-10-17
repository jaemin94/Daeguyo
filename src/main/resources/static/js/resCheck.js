document.addEventListener('DOMContentLoaded', function () {
const subBtn = document.getElementById("submit_id");
subBtn.addEventListener('click',function(){
const order_id = document.getElementById("order_id").value;
console.log(order_id);
if(order_id){
axios.get("/orderCheck1?order_id="+ order_id)
.then(response => {
    console.log(response.data);
    location.href = '/orderCheck1?order_id=' + order_id;

})
.catch(error =>{
    console.error();
})
}else{
    axios.get("/orderCheck")
    .then(response => {
    location.reload();
    })
    .catch(error =>{
        console.error();
    })
}

});
});



    $('.minus-btn').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());

    if (value > 1) {
    value = value - 1;
} else {
    value = 0;
}
    $input.val(value);

});

    $('.plus-btn').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());

    if (value < 100) {
    value = value + 1;
} else {
    value =100;
}

    $input.val(value);
});

    function changeStatus(par, command,qtyProduct) {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.status === 200) {
                location.reload();
            }
        }
        xhr.open('GET', "../controller");
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send("command=pageCart&productId=par&action=" + command + "&qty=" + qtyProduct);
    }

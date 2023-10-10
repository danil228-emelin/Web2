var finalX = 0;
var finalY = 0;
canvas.addEventListener('click', () => {
    console.log("x:".concat(finalX).concat(",y:").concat(finalY));
    drawPoint(ctx, finalX, finalY, 'red', 5)
})

function drawPoint(context, x, y, color, size) {
    if (color == null) {
        color = '#000';
    }
    if (size == null) {
        size = 5;
    }
    var radius = 0.5 * size;
    var pointX = Math.round(x - radius);
    var pointY = Math.round(y - radius);
    context.beginPath();
    context.fillStyle = color;
    context.fillRect(pointX, pointY, size, size);
    context.fill();
}

canvas.addEventListener('mousedown', function (e) {
    getCursorPosition(canvas, e);
})

function getCursorPosition(canvas, event) {
    const rect = canvas.getBoundingClientRect()
    const x = event.clientX - rect.left
    const y = event.clientY - rect.top
    finalX = x;
    finalY = y;
}



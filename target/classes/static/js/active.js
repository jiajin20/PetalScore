
var index = 0;

$('.content-l button').click(function () {
    index++;
    if (index % 2 === 1) {
        // 第一次点击
        $('.content-l h2').css({
            transform: 'translate(-180px, 10px)',
            width: '50%'
        });
        $('.content-l span').css({
            transform: 'translate(-180px, 10px)',
            width: '50%',
            font: '400 25px "Gochi Hand"',
            lineHeight: '1.5'
        });
        $('.content-l button').css({
            width: '300px',
            height: '55px',
            top: '20%',
            backgroundPositionX: '70px',
            transform: 'rotate(180deg)'
        });
        $('.circle').css({
            width: '400px',
            height: '400px',
            left: '57%',
            top: '50%',
            border: '23px #ffffff dashed'
        });
        $('.content-l img').css({
            bottom: '0px'
        });
        $('.content-l').css({
            width: '63%'
        });
        $('.content-r').css({
            width: '37%'
        });
        $('.img').css({
            padding: '10px',
            transform: 'scale(1)'
        });
        $('.img img').css({
            transform: 'rotate(0)',
            width: '100%',
            height: '100%',
            boxShadow: 'none',
            borderRadius: '30px'
        });

        // 伪元素样式
        var style = document.createElement('style');
        style.innerHTML = `
            .content::before {
                width: 900px;
                height: 900px;
                left: 49%;
                bottom: -27%;
            }
        `;
        document.head.appendChild(style);

        var styleAfter = document.createElement('style');
        styleAfter.innerHTML = `
            .content::after {
                opacity: 0;
            }
        `;
        document.head.appendChild(styleAfter);

    } else {
        // 第二次点击
        $('.content-l h2').css({
            transform: 'translateY(-30px)',
            width: '80%'
        });
        $('.content-l span').css({
            transform: 'translateY(-30px)',
            width: '80%',
            font: '100 20px "Gochi Hand"',
            lineHeight: '2'
        });
        $('.content-l button').css({
            width: '150px',
            height: '50px',
            top: '80%',
            backgroundPositionX: '0',
            transform: 'rotate(0deg)'
        });
        $('.circle').css({
            width: '800px',
            height: '800px',
            left: '-440px',
            bottom: '-440px',
            border: '43px #fff dashed'
        });
        $('.content-l img').css({
            bottom: '-500px'
        });
        $('.content-l').css({
            width: '40%'
        });
        $('.content-r').css({
            width: '60%'
        });
        $('.img').css({
            padding: '0px'
        });
        $('.img img').css({
            width: '60%',
            height: '90%',
            boxShadow: '10px 20px 28px #0c48888c',
            borderRadius: '0px'
        });

        // 伪元素样式
        var styleBefore = document.createElement('style');
        styleBefore.innerHTML = `
            .content::before {
                width: 700px;
                height: 700px;
                left: -420px;
                bottom: -420px;
            }
        `;
        document.head.appendChild(styleBefore);

        var styleAfterVisible = document.createElement('style');
        styleAfterVisible.innerHTML = `
            .content::after {
                opacity: 1;
            }
        `;
        document.head.appendChild(styleAfterVisible);
    }
});
console.log($('.content-l button'));  // 检查是否获取到了 button 元素
console.log($('.content-l h2'));     // 检查是否获取到了 h2 元素
console.log($('.content-l span'));   // 检查是否获取到了 span 元素


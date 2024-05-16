window.addEventListener('vaadin-router-location-changed', () => {
    const calendarWrapper = document.querySelector('.calendar-component-wrapper');
    console.log(calendarWrapper);
    if (calendarWrapper) {
        var customNextArrow = '<button type="button" class="slick-next"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" fill="#EF9B5F" version="1.1" id="Layer_1" viewBox="0 0 330 330" xml:space="preserve"> <path id="XMLID_225_" d="M325.607,79.393c-5.857-5.857-15.355-5.858-21.213,0.001l-139.39,139.393L25.607,79.393  c-5.857-5.857-15.355-5.858-21.213,0.001c-5.858,5.858-5.858,15.355,0,21.213l150.004,150c2.813,2.813,6.628,4.393,10.606,4.393  s7.794-1.581,10.606-4.394l149.996-150C331.465,94.749,331.465,85.251,325.607,79.393z"/> </svg></button>';
        var customPrevArrow = '<button type="button" class="slick-prev"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" fill="#EF9B5F" version="1.1" id="Layer_1" viewBox="0 0 330 330" xml:space="preserve"> <path id="XMLID_225_" d="M325.607,79.393c-5.857-5.857-15.355-5.858-21.213,0.001l-139.39,139.393L25.607,79.393  c-5.857-5.857-15.355-5.858-21.213,0.001c-5.858,5.858-5.858,15.355,0,21.213l150.004,150c2.813,2.813,6.628,4.393,10.606,4.393  s7.794-1.581,10.606-4.394l149.996-150C331.465,94.749,331.465,85.251,325.607,79.393z"/> </svg></button>';

        $(calendarWrapper).slick({
            infinite: true,
            slidesToShow: 3,
            slidesToScroll: 1,
            vertical: true,
            touchMove: true,
            swipe: true,
            centerMode: true,
            touchThreshhold: 5,
            nextArrow: customNextArrow,
            prevArrow: customPrevArrow,


        });

        calendarWrapper.addEventListener('scroll', function() {
            var scrollTop = this.scrollTop;
            var slideIndex = Math.round(scrollTop / this.clientHeight);
            $('.calendar-component-wrapper').slick('slickGoTo', slideIndex);
        });
    }
});
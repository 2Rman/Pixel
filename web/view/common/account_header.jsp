
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <div class="logo">
        <img src="../image/Pixel.png" alt="PixeL">
    </div>
    <div class="right_container">
        <div class="right_text_container">
            Доброго дня, ${sessionScope.get("username")}!
        </div>
        <div class="right_button_container">
            <input type="submit" class="btn_header" value="Настройки" formmethod="post" formaction="/pixel?command=settings">
            <input type="submit" class="btn_header" value="Копилка" formmethod="post" formaction="/pixel?command=savings">
        </div>
    </div>
</div>

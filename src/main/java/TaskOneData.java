public final class TaskOneData {
    static String expectedTitle = "Добровольное медицинское страхование"; //Тут я подумал, что по правилам инкапсуляции
    static String firstName = "Егор";//надо бы сделать Alt+Insert и дать всему этому геттеры и сеттеры, но не уверен,
    static String secondName = "Холин"; //что это надо делать в классе, созданном для хранения данных
    static String middleName = "Евгеньевич";

    static String regionName = "Москва";
    static String phone = "9031234567";
    static String mail = "qwertyqwerty";
    static String comments = "No comments";
    static String preferDate = "30.10.2019";

    static String menuInsurance = "//A[@href='/service/ins/index.wbp'][text()='Страховой случай']/../..//A[@href='/products/private_person/health/index.wbp'][text()='Здоровье']";
    static String menuDmsInsurance = "/html/body/div[5]/div/div[1]/div[1]/div/a[1]";
    static String sendStatement = "//a [contains(@class, \"btn-default text\")]";
    static String statementFooter = "//DIV[@class='form-footer']/self::DIV";
    static String titleChecker = "//b[contains(text(), 'Заявка на добровольное медицинское страхование')]";

    static String secondNameName = "LastName";
    static String fistNameName = "FirstName";
    static String middleNameName = "MiddleName";
    static String phonePath = "//input[contains(@data-bind, 'Phone')]";
    static String mailName = "Email";
    static String preferDateName = "ContactDate";
    static String commentsName = "Comment";
    static String checkboxClassName = "checkbox";
    static String buttonId = "button-m";
    static String regionSelecterName = "Region";
    static String regionPath = "//select[@name='Region']/option[text()='Москва']";
}

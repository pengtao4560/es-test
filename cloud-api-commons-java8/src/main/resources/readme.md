
[java8新特性](Java8%20新特性.pdf)

LocalDate、LocalTime、LocalDateTime 类的实例是不可变的对象，
分别表示使用 ISO-8601日历系统的日期、时间、日期和时间。
它们提供了简单的日期或时间，并不包含当前的时间信息。也不包含与时区相关的信息。
注：ISO-8601日历系统是国际标准化组织制定的现代公民的日期和时间的表示法

LocalDate 使用 ISO-8601日历系统的日期
LocalTime 使用 ISO-8601日历系统的时间
LocalDateTime 使用 ISO-8601日历系统的日期和时间

    方法                                                  用法                                           代码

    now()                                               静态方法，根据当前时间创建对象 
                                                                                                        LocalDate localDate = LocalDate.now();
                                                                                                        LocalTime localTime = LocalTime.now();
                                                                                                        LocalDateTime localDateTime = LocalDateTime.now();
    of()                                                 静态方法，根据指定日期/时间创建对象
                                                                                                        LocalDate localDate = LocalDate.of(2016, 10, 26);
                                                                                                        LocalTime localTime = LocalTime.of(02, 22, 56);
                                                                                                        LocalDateTime localDateTime = LocalDateTime.of(2016, 10,
                                                                                                        26, 12, 10, 55);
    plusDays, plusWeeks, plusMonths, plusYears
    向                                                   当前 LocalDate 对象添加几天、几周、几个月、几年

    minusDays, minusWeeks, minusMonths, minusYears      从当前 LocalDate 对象减去几天、几周、几个月、几年

    plus, minus                                         添加或减少一个 Duration 或 Period

    withDayOfMonth, withDayOfYear, withMonth, withYear  将月份天数、年份天数、月份、年 份 修 改 为 指 定 的 值 并 返 回 新 的 LocalDate 对象

    getDayOfMonth                                       获得月份天数(1-31)

    getDayOfYear                                        获得年份天数(1-366)

    getDayOfWeek                                        获得星期几(返回一个 DayOfWeek 枚举值)

    getMonth                                            获得月份, 返回一个 Month 枚举值

    getMonthValue                                       获得月份(1-12)

    getYear                                             获得年份

    until                                               获得两个日期之间的 Period 对象，或者指定 ChronoUnits 的数字

    isBefore, isAfter                                   比较两个 LocalDate

    isLeapYear                                          判断是否是闰年

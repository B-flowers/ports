package com.ports.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ports.utils.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CustomObjectMapper extends ObjectMapper {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean camelCaseToLowerCaseWithUnderscores = false;
    private String dateFormatPattern;

    public void setCamelCaseToLowerCaseWithUnderscores(boolean camelCaseToLowerCaseWithUnderscores) {
        this.camelCaseToLowerCaseWithUnderscores = camelCaseToLowerCaseWithUnderscores;
    }

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

    public void init() {
        // 进行缩进输出
        configure(SerializationFeature.INDENT_OUTPUT, true);
        // 将驼峰转为下划线
//        if (camelCaseToLowerCaseWithUnderscores) {
//            setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
//        }
        // 进行日期格式化
        if (!StringUtils.isEmpty(dateFormatPattern)) {
            DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
            setDateFormat(dateFormat);
        }
    }
}

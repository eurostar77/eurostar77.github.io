package com.bueffeltier.data.jdbc;

// todo: wertebereiche int und varchar festlegen.
public enum MaterialTablesDV
{
	PAGE(
	    "page",
	    "CREATE TABLE IF NOT EXISTS page ("
	        + "id bigint auto_increment PRIMARY KEY, " //
	        + "title varchar(255) UNIQUE, " // 1
	        + "path varchar(255), " // 2
	        + "layout INT, " // 3
	        + "permission INT, " // 4
	        + "forward_to varchar(255), " // 5
	        + "author varchar(255), " // 6
	        + "cache_time varchar(255), " // 7
	        + "create_sitemap BOOLEAN, " // 8
	        + "css_class varchar(255), " // 9
	        + "description varchar(255), " // 10
	        + "page_type varchar(255), " // 11
	        + "hide_in_nav BOOLEAN, " // 12
	        + "include_cache BOOLEAN, " // 13
	        + "include_layout BOOLEAN, " // 14
	        + "is_protected BOOLEAN, " // 15
	        + "keywords varchar(255), " // 16
	        + "language varchar(255), " // 17
	        + "last_version varchar(255), " // 18
	        + "published BOOLEAN, " // 19
	        + "sitemap_name varchar(255), " // 20
	        + "no_follow BOOLEAN, " // 21
	        + "no_index BOOLEAN, " // 22
	        + "internal_name varchar(255), " // 23
	        + "url_alias varchar(255)" // 24
	        + ")"
		//
	    , "DROP TABLE IF EXISTS page", false
	),
	PAGE_TEST(
	    "page_test",
	    "CREATE TABLE IF NOT EXISTS page_test ("
	        + "id bigint auto_increment PRIMARY KEY, " //
	        + "title varchar(255) UNIQUE, " // 1
	        + "path varchar(255), " // 2
	        + "layout INT, " // 3
	        + "permission INT, " // 4
	        + "forward_to varchar(255), " // 5
	        + "author varchar(255), " // 6
	        + "cache_time varchar(255), " // 7
	        + "create_sitemap BOOLEAN, " // 8
	        + "css_class varchar(255), " // 9
	        + "description varchar(255), " // 10
	        + "page_type varchar(255), " // 11
	        + "hide_in_nav BOOLEAN, " // 12
	        + "include_cache BOOLEAN, " // 13
	        + "include_layout BOOLEAN, " // 14
	        + "is_protected BOOLEAN, " // 15
	        + "keywords varchar(255), " // 16
	        + "language varchar(255), " // 17
	        + "last_version varchar(255), " // 18
	        + "published BOOLEAN, " // 19
	        + "sitemap_name varchar(255), " // 20
	        + "no_follow BOOLEAN, " // 21
	        + "no_index BOOLEAN, " // 22
	        + "internal_name varchar(255), " // 23
	        + "url_alias varchar(255)" // 24
	        + ")"

		//
	    , "DROP TABLE IF EXISTS page_test", true
	),
	ARTICLE(
	    "article",
	    "CREATE TABLE IF NOT EXISTS article ("
	        + "id BIGINT AUTO_INCREMENT PRIMARY KEY, " //
	        + "parent_id BIGINT, " // 1
	        + "sorting INT, " // 2
	        + "title VARCHAR(255) UNIQUE, " // 3
	        + "author VARCHAR(255), " // 4
	        + "teaser_text VARCHAR(255), " // 5
	        + "teaser_image_path VARCHAR(255), " // 6
	        + "show_teaser BOOLEAN, " // 7
	        + "last_edit_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " // 8
	        + "is_published BOOLEAN, " // 9
	        + "keywords VARCHAR(255), " // 10
	        + "teaser_css_class VARCHAR(255), " // 11
	        + "teaser_css_id VARCHAR(255), " // 12
	        + "css_class VARCHAR(255), " // 13
	        + "css_id VARCHAR(255), " // 14
	        + "container_tag VARCHAR(255), " // 15
	        + "create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " // 16
	        + "show_from TIMESTAMP NULL, " // 17
	        + "show_until TIMESTAMP NULL, " // 18
	        + "layout_column VARCHAR(255), " // 19
	        + "page_path VARCHAR(255)" // 20
	        + ")"

		//
	    , "DROP TABLE IF EXISTS article", false
	),
	ARTICLE_TEST(
	    "article_test",
	    "CREATE TABLE IF NOT EXISTS article_test ("
	        + "id BIGINT AUTO_INCREMENT PRIMARY KEY, " //
	        + "parent_id BIGINT, " // 1
	        + "sorting INT, " // 2
	        + "title VARCHAR(255) UNIQUE, " // 3
	        + "author VARCHAR(255), " // 4
	        + "teaser_text VARCHAR(255), " // 5
	        + "teaser_image_path VARCHAR(255), " // 6
	        + "show_teaser BOOLEAN, " // 7
	        + "last_edit_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " // 8
	        + "is_published BOOLEAN, " // 9
	        + "keywords VARCHAR(255), " // 10
	        + "teaser_css_class VARCHAR(255), " // 11
	        + "teaser_css_id VARCHAR(255), " // 12
	        + "css_class VARCHAR(255), " // 13
	        + "css_id VARCHAR(255), " // 14
	        + "container_tag VARCHAR(255), " // 15
	        + "create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " // 16
	        + "show_from TIMESTAMP NULL, " // 17
	        + "show_until TIMESTAMP NULL, " // 18
	        + "layout_column VARCHAR(255), " // 19
	        + "page_path VARCHAR(255)" // 20
	        + ")"
		//
	    , "DROP TABLE IF EXISTS article_test", true
	),
	ELEMENT(
	    "element",
	    "CREATE TABLE IF NOT EXISTS element ("
	        + "id BIGINT AUTO_INCREMENT PRIMARY KEY, " //
	        + "parent_id BIGINT, " // 1
	        + "sorting INT, " // 2
	        + "type VARCHAR(255), " // 3
	        + "is_dynamic BOOLEAN, " // 4
	        + "html VARCHAR(255), " // 5
	        + "headline_type INT, " // 6
	        + "headline_text VARCHAR(255), " // 7
	        + "guests_only BOOLEAN, " // 8
	        + "css_id VARCHAR(255), " // 9
	        + "css_class VARCHAR(255), " // 10
	        + "hide BOOLEAN, " // 11
	        + "show_from TIMESTAMP NULL, " // 12
	        + "show_until TIMESTAMP NULL" // 13
	        + ")"
		//
	    , "DROP TABLE IF EXISTS element", false
	),
	ELEMENT_TEST(
	    "element_test",
	    "CREATE TABLE IF NOT EXISTS element_test ("
	        + "id BIGINT AUTO_INCREMENT PRIMARY KEY, " //
	        + "parent_id BIGINT, " // 1
	        + "sorting INT, " // 2
	        + "type VARCHAR(255), " // 3
	        + "is_dynamic BOOLEAN, " // 4
	        + "html VARCHAR(255), " // 5
	        + "headline_type INT, " // 6
	        + "headline_text VARCHAR(255), " // 7
	        + "guests_only BOOLEAN, " // 8
	        + "css_id VARCHAR(255), " // 9
	        + "css_class VARCHAR(255), " // 10
	        + "hide BOOLEAN, " // 11
	        + "show_from TIMESTAMP NULL, " // 12
	        + "show_until TIMESTAMP NULL" // 13
	        + ")"
		//
	    , "DROP TABLE IF EXISTS element_test", true
	),
	USER(
	    "user",
	    "CREATE TABLE IF NOT EXISTS user ("
	        + "id BIGINT AUTO_INCREMENT PRIMARY KEY, " //
	        + "name VARCHAR(255) UNIQUE, " // 1
	        + "password VARCHAR(255), " // 2
	        + "permission INT(10), " // 3
	        + "email VARCHAR(255) UNIQUE, " // 4
	        + "is_active BOOLEAN, " // 5
	        + "activation_key VARCHAR(255) UNIQUE, " // 6
	        + "session_token VARCHAR(255) UNIQUE, " // 7
	        + "activation_key_experiation_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP," // 8
	        + "is_anonymous BOOLEAN," // 9
	        + "last_visit_date TIMESTAMP," // 10
	        + "account_creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," // 11
	        + "session_id VARCHAR(36) UNIQUE" // 12
	        + ")"
		//
	    , "DROP TABLE IF EXISTS user", false
	),
	USER_TEST(
	    "user_test",
	    "CREATE TABLE IF NOT EXISTS user_test ("
	        + "id BIGINT AUTO_INCREMENT PRIMARY KEY, " //
	        + "name VARCHAR(255) UNIQUE, " // 1
	        + "password VARCHAR(255), " // 2
	        + "permission INT(10), " // 3
	        + "email VARCHAR(255) UNIQUE, " // 4
	        + "is_active BOOLEAN, " // 5
	        + "activation_key VARCHAR(255) UNIQUE, " // 6
	        + "session_token VARCHAR(255) UNIQUE, " // 7
	        + "activation_key_experiation_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP," // 8
	        + "is_anonymous BOOLEAN" // 9
	        + "last_visit_date TIMESTAMP," // 10
	        + "account_creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," // 11
	        + "session_id VARCHAR(36) UNIQUE" // 12
	        + ")"
		//
	    , "DROP TABLE IF EXISTS user_test", true
	),
	INFO_BIT(
	    "info_bit",
	    "CREATE TABLE IF NOT EXISTS info_bit ("
	        + "info_bit_id BIGINT AUTO_INCREMENT PRIMARY KEY, " //
	        + "info_bit_question VARCHAR(255), " // 1
	        + "info_bit_answer VARCHAR(255), " // 2
	        + "info_bit_right_answer_count INT(10), " // 3
	        + "info_bit_wrong_answer_count INT(10), " // 4
	        + "task_id BIGINT, " // 5
	        + "task_name VARCHAR(255), " // 6
	        + "lection_id BIGINT, " // 7
	        + "lection_name VARCHAR(255), " // 8
	        + "user_id BIGINT UNIQUE" // 9
	        + ")"
		//
	    , "DROP TABLE IF EXISTS info_bit", false
	),
	INFO_BIT_TEST(
	    "info_bit",
	    "CREATE TABLE IF NOT EXISTS info_bit_test ("
	        + "info_bit_id BIGINT AUTO_INCREMENT PRIMARY KEY, " //
	        + "info_bit_question VARCHAR(255), " // 1
	        + "info_bit_answer VARCHAR(255), " // 2
	        + "info_bit_right_answer_count INT(10), " // 3
	        + "info_bit_wrong_answer_count INT(10), " // 4
	        + "task_id BIGINT, " // 5
	        + "task_name VARCHAR(255), " // 6
	        + "lection_id BIGINT, " // 7
	        + "lection_name VARCHAR(255), " // 8
	        + "user_id BIGINT UNIQUE" // 9
	        + ")"
		//
	    , "DROP TABLE IF EXISTS info_bit_test", true
	),
	//
	;

	private final String name;
	private final String create;
	private final String drop;
	private final boolean isTest;

	private MaterialTablesDV(
	    String name, String create, String drop, boolean isTest
	)
	{
		this.name = name;
		this.create = create;
		this.drop = drop;
		this.isTest = isTest;
	}

	public String getName()
	{
		return name;
	}

	public String getCreate()
	{
		return create;
	}

	public String getDrop()
	{
		return drop;
	}

	public boolean isTest()
	{
		return isTest;
	}

	@Override
	public String toString()
	{
		return getName();
	}
}

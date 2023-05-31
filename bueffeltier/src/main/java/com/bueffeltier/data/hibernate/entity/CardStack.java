package com.bueffeltier.data.hibernate.entity;
//package com.mycompany.bubbles.database.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
///**
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//@Entity
//@Table(name = "tbl_card_stack", catalog = "bueffeltier")
//public class CardStack implements java.io.Serializable
//{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "tr_id", unique = true, nullable = false, length = 10)
//    private int id;
////
////    @Column(name = "tr_order", nullable = true, length = 10)
////    private int order;
////
////    @Column(name = "tr_title", nullable = true, length = 255)
////    private String title;
////
////    @Column(name = "tr_author", nullable = true, length = 45)
////    private String author;
////
////    @Column(name = "tr_teaser_text", nullable = true, length = 255)
////    private String teaserText;
////
////    @Column(name = "tr_teaser_image_path", nullable = true, length = 255)
////    private String teaserImagePath;
////
//////    @Column(name = "tr_last_edit_date", nullable = true, length = 8)
//////    private String lastEditDate;
////    @Type(type = "org.hibernate.type.NumericBooleanType")
////    @Column(columnDefinition = "TINYINT", name = "tr_show_teaser",
////            nullable = false, length = 1)
////    private Boolean showTeaser;
////
////    @Type(type = "org.hibernate.type.NumericBooleanType")
////    @Column(columnDefinition = "TINYINT", name = "tr_is_published",
////            nullable = false, length = 1)
////    private Boolean isPublished;
////
////    @Column(name = "tr_keywords", nullable = true, length = 255)
////    private String keywords;
////
////    @Column(name = "tr_teaser_css_class", nullable = true, length = 255)
////    private String teaserCssClass;
////
////    @Column(name = "tr_teaser_css_id", nullable = true, length = 255)
////    private String teaserCssId;
////
////    @Column(name = "tr_css_class", nullable = true, length = 255)
////    private String cssClass;
////
////    @Column(name = "tr_css_id", nullable = true, length = 255)
////    private String cssId;
////
////    @Column(name = "tr_container_tag", nullable = true, length = 255)
////    private String containerTag;
//
//    /**
//     *
//     */
//    public CardStack()
//    {
//
//    }
//
//    /**
//     *
//     * @return
//     */
//    public int getId()
//    {
//        return this.id;
//    }
//
//    /**
//     *
//     * @param nummer
//     */
//    public void setId(int nummer)
//    {
//        this.id = nummer;
//    }
//
////    @Override
////    public String toString()
////    {
////        return "Article{" + "\n"
////                + "parentId=" + parentId + "\n"
////                + ", id=" + id + "\n"
////                + ", order=" + order + "\n"
////                + ", title=" + title + "\n"
////                + ", author=" + author + "\n"
////                //                + ", lastEditDate=" + lastEditDate + "\n"
////                + ", showTeaser=" + showTeaser + "\n"
////                + ", isPublished=" + isPublished + "\n"
////                + ", keywords=" + keywords + "\n"
////                + ", teaserCssClass=" + teaserCssClass + "\n"
////                + ", teaserCssId=" + teaserCssId + "\n"
////                + ", cssClass=" + cssClass + "\n"
////                + ", cssId=" + cssId + "\n"
////                + ", containerTag=" + containerTag + "\n"
////                + '}';
////    }
//}

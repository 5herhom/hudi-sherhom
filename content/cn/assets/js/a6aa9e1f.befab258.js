"use strict";(self.webpackChunkhudi=self.webpackChunkhudi||[]).push([[93089],{2754:function(e,t,a){a.r(t),a.d(t,{default:function(){return g}});var l=a(67294),r=a(52263),n=a(76200),i=a(41916),s=a(39960),m=a(95999);var o=function(e){var t=e.metadata,a=t.previousPage,r=t.nextPage;return l.createElement("nav",{className:"pagination-nav","aria-label":(0,m.I)({id:"theme.blog.paginator.navAriaLabel",message:"Blog list page navigation",description:"The ARIA label for the blog pagination"})},l.createElement("div",{className:"pagination-nav__item"},a&&l.createElement(s.Z,{className:"pagination-nav__link",to:a},l.createElement("div",{className:"pagination-nav__label"},"\xab"," ",l.createElement(m.Z,{id:"theme.blog.paginator.newerEntries",description:"The label used to navigate to the newer blog posts page (previous page)"},"Newer Entries")))),l.createElement("div",{className:"pagination-nav__item pagination-nav__item--next"},r&&l.createElement(s.Z,{className:"pagination-nav__link",to:r},l.createElement("div",{className:"pagination-nav__label"},l.createElement(m.Z,{id:"theme.blog.paginator.olderEntries",description:"The label used to navigate to the older blog posts page (next page)"},"Older Entries")," ","\xbb"))))},c=a(63616);var g=function(e){var t=e.metadata,a=e.items,s=e.sidebar,m=(0,r.Z)().siteConfig.title,g=t.blogDescription,d=t.blogTitle,u="/"===t.permalink?m:d;return l.createElement(n.Z,{title:u,description:g,wrapperClassName:c.kM.wrapper.blogPages,pageClassName:c.kM.page.blogListPage,searchMetadata:{tag:"blog_posts_list"},sidebar:s},a.map((function(e){var t=e.content;return l.createElement(i.Z,{key:t.metadata.permalink,frontMatter:t.frontMatter,assets:t.assets,metadata:t.metadata,truncated:t.metadata.truncated},l.createElement(t,null))})),l.createElement(o,{metadata:t}))}},86753:function(e,t,a){a.d(t,{Z:function(){return d}});var l=a(67294),r=a(95999),n=a(87462),i=a(63366),s=a(86010),m="iconEdit_mS5F",o=["className"];var c=function(e){var t=e.className,a=(0,i.Z)(e,o);return l.createElement("svg",(0,n.Z)({fill:"currentColor",height:"20",width:"20",viewBox:"0 0 40 40",className:(0,s.Z)(m,t),"aria-hidden":"true"},a),l.createElement("g",null,l.createElement("path",{d:"m34.5 11.7l-3 3.1-6.3-6.3 3.1-3q0.5-0.5 1.2-0.5t1.1 0.5l3.9 3.9q0.5 0.4 0.5 1.1t-0.5 1.2z m-29.5 17.1l18.4-18.5 6.3 6.3-18.4 18.4h-6.3v-6.2z"})))},g=a(63616);function d(e){var t=e.editUrl;return l.createElement("a",{href:t,target:"_blank",rel:"noreferrer noopener",className:g.kM.common.editThisPage},l.createElement(c,null),l.createElement(r.Z,{id:"theme.common.editThisPage",description:"The link label to edit the current page"},"Edit this page"))}},8727:function(e,t,a){a.d(t,{Z:function(){return u}});var l=a(67294),r=a(86010),n=a(95999),i=a(39960),s="tag_WK-t",m="tagRegular_LXbV",o="tagWithCount_S5Zl";var c=function(e){var t,a=e.permalink,n=e.name,c=e.count;return l.createElement(i.Z,{href:a,className:(0,r.Z)(s,(t={},t[m]=!c,t[o]=c,t))},n,c&&l.createElement("span",null,c))},g="tags_NBRY",d="tag_F03v";function u(e){var t=e.tags;return l.createElement(l.Fragment,null,l.createElement("b",null,l.createElement(n.Z,{id:"theme.tags.tagsListLabel",description:"The label alongside a tag list"},"Tags:")),l.createElement("ul",{className:(0,r.Z)(g,"padding--none","margin-left--sm")},t.map((function(e){var t=e.label,a=e.permalink;return l.createElement("li",{key:a,className:d},l.createElement(c,{name:t,permalink:a}))}))))}},76200:function(e,t,a){a.d(t,{Z:function(){return h}});var l=a(63366),r=a(67294),n=a(86010),i=a(92582),s=a(39960),m="sidebar_q+wC",o="sidebarItemTitle_9G5K",c="sidebarItemList_6T4b",g="sidebarItem_cjdF",d="sidebarItemLink_zyXk",u="sidebarItemLinkActive_wcJs",p=a(95999);function v(e){var t=e.sidebar;return 0===t.items.length?null:r.createElement("nav",{className:(0,n.Z)(m,"thin-scrollbar"),"aria-label":(0,p.I)({id:"theme.blog.sidebar.navAriaLabel",message:"Blog recent posts navigation",description:"The ARIA label for recent posts in the blog sidebar"})},r.createElement("div",{className:(0,n.Z)(o,"margin-bottom--md")},t.title),r.createElement("ul",{className:c},t.items.map((function(e){return r.createElement("li",{key:e.permalink,className:g},r.createElement(s.Z,{isNavLink:!0,to:e.permalink,className:d,activeClassName:u},e.title))}))))}var b=["sidebar","toc","children"];var h=function(e){var t=e.sidebar,a=e.toc,s=e.children,m=(0,l.Z)(e,b),o=t&&t.items.length>0;return r.createElement(i.Z,m,r.createElement("div",{className:"container margin-vert--lg"},r.createElement("div",{className:"row"},o&&r.createElement("aside",{className:"col col--3"},r.createElement(v,{sidebar:t})),r.createElement("main",{className:(0,n.Z)("col",{"col--7":o,"col--9 col--offset-2":!o}),itemScope:!0,itemType:"http://schema.org/Blog"},s),a&&r.createElement("div",{className:"col col--2"},a))))}},41916:function(e,t,a){a.d(t,{Z:function(){return E}});var l=a(67294),r=a(86010),n=a(3905),i=a(95999),s=a(39960),m=a(44996),o=a(63616),c=a(67707),g=a(86753),d={blogPostTitle:"blogPostTitle_RC3s",blogPostPageTile:"blogPostPageTile_BsLs",blogPostData:"blogPostData_A2Le",blogPostDetailsFull:"blogPostDetailsFull_2lop","blog-list-page":"blog-list-page_Jl5M",container:"container_EXwA",row:"row_DZ33"},u=a(8727),p="image_9q7L";var v=function(e){var t=e.author,a=t.name,r=t.title,n=t.url,i=t.imageURL;return l.createElement("div",{className:"avatar margin-bottom--sm"},i&&l.createElement(s.Z,{className:"avatar__photo-link avatar__photo",href:n},l.createElement("img",{className:p,src:i,alt:a})),a&&l.createElement("div",{className:"avatar__intro",itemProp:"author",itemScope:!0,itemType:"https://schema.org/Person"},l.createElement("div",{className:"avatar__name"},l.createElement(s.Z,{href:n,itemProp:"url"},l.createElement("span",{itemProp:"name"},a))),r&&l.createElement("small",{className:"avatar__subtitle",itemProp:"description"},r)))},b="authorCol_8c0z";function h(e){var t=e.authors,a=e.assets;return 0===t.length?null:l.createElement("div",{className:"row margin-top--md margin-bottom--sm"},t.map((function(e,t){var n;return l.createElement("div",{className:(0,r.Z)("col col--6",b),key:t},l.createElement(v,{author:Object.assign({},e,{imageURL:null!=(n=a.authorsImageUrls[t])?n:e.imageURL})}))})))}var E=function(e){var t,a,p,v,b,E=(v=(0,o.c2)().selectMessage,function(e){var t=Math.ceil(e);return v(t,(0,i.I)({id:"theme.blog.post.readingTime.plurals",description:'Pluralized label for "{readingTime} min read". Use as much plural forms (separated by "|") as your language support (see https://www.unicode.org/cldr/cldr-aux/charts/34/supplemental/language_plural_rules.html)',message:"One min read|{readingTime} min read"},{readingTime:t}))}),_=(0,m.C)().withBaseUrl,f=e.children,N=e.frontMatter,P=e.assets,Z=e.metadata,T=e.truncated,k=e.isBlogPostPage,w=void 0!==k&&k,L=Z.date,C=Z.formattedDate,y=Z.permalink,I=Z.tags,B=Z.readingTime,M=Z.title,A=Z.editUrl,D=Z.authors,U=null!=(t=null!=(a=P.image)?a:N.image)?t:"/assets/images/logo-big.png",F=!w&&T,R=I.length>0;return l.createElement("article",{className:w?void 0:"blog-list-item",itemProp:"blogPost",itemScope:!0,itemType:"http://schema.org/BlogPosting"},(b=w?"h1":"h2",l.createElement("header",null,!w&&U&&l.createElement("div",{className:"col blogThumbnail",itemProp:"blogThumbnail"},l.createElement(s.Z,{itemProp:"url",to:y},l.createElement("img",{src:_(U,{absolute:!0})}))),l.createElement(b,{className:d.blogPostTitle,itemProp:"headline"},w?l.createElement(b,{className:d.blogPostPageTitle,itemProp:"headline"},M):l.createElement(s.Z,{itemProp:"url",to:y},l.createElement(b,{className:d.blogPostTitle,itemProp:"headline"},M))),l.createElement("div",{className:(0,r.Z)(d.blogPostData,"margin-vert--md")},l.createElement("time",{dateTime:L,itemProp:"datePublished"},C),void 0!==B&&l.createElement(l.Fragment,null," \xb7 ",E(B))),w&&l.createElement(h,{authors:D,assets:P}))),w&&l.createElement("div",{className:"markdown",itemProp:"articleBody"},l.createElement(n.Zo,{components:c.Z},f)),(R||T)&&l.createElement("footer",{className:(0,r.Z)("row docusaurus-mt-lg",(p={},p[d.blogPostDetailsFull]=w,p))},R&&l.createElement("div",{className:(0,r.Z)("col",{"col--9":F})},l.createElement(u.Z,{tags:I})),w&&A&&l.createElement("div",{className:"col margin-top--sm"},l.createElement(g.Z,{editUrl:A}))))}}}]);
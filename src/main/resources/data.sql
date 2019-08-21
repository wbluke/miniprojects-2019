insert into member(email, name, nick_name, password, profile_image_url)
values('abc@naver.com','nick','isKing','$2a$10$IHDDc06yJr4v9JpXysrYceM0wc/Tgngi4qtWHDhgKWibMDi5uRhMe','https://tpc.googlesyndication.com/simgad/9364860944927588759?sqp=4sqPyQQ7QjkqNxABHQAAtEIgASgBMAk4A0DwkwlYAWBfcAKAAQGIAQGdAQAAgD-oAQGwAYCt4gS4AV_FAS2ynT4&rs=AOga4qlnOwkaT65pLcJFj7dIqYTykG0kqA');

insert into member(email, name, nick_name, password, profile_image_url)
values('abc@gmai.com','king','isNick','$2a$10$IHDDc06yJr4v9JpXysrYceM0wc/Tgngi4qtWHDhgKWibMDi5uRhMe','https://tpc.googlesyndication.com/simgad/9364860944927588759?sqp=4sqPyQQ7QjkqNxABHQAAtEIgASgBMAk4A0DwkwlYAWBfcAKAAQGIAQGdAQAAgD-oAQGwAYCt4gS4AV_FAS2ynT4&rs=AOga4qlnOwkaT65pLcJFj7dIqYTykG0kqA');


insert into article(contents, image_url, author) values('닉은 왕이다.', 'https://zzazanstagram-zzazan.s3.ap-northeast-2.amazonaws.com/upload/2019-08-21T09%3A27%3A32.100Apple-ios-13-sign-in-screen-iphone-xs-06032019.jpg', '1');
insert into article(contents, image_url, author) values('왕은 닉이다.', 'https://zzazanstagram-zzazan.s3.ap-northeast-2.amazonaws.com/upload/2019-08-21T09%3A27%3A32.100Apple-ios-13-sign-in-screen-iphone-xs-06032019.jpg', '1');

insert into comment(contents, article_id, commenter_id) values('닉은 왕이다', '1', '1');
insert into comment(contents, article_id, commenter_id) values('닉은 왕따이다', '1', '1');
insert into comment(contents, article_id, commenter_id) values('닉은 왕꿈틀이다', '1', '1');

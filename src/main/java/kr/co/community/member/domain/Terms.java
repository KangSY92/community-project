package kr.co.community.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Terms {

	private int termsId;
	private String title;
	private String content;
	private String marketing;
	private String createDate;
	private String required;
}

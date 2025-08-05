package kr.co.community.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agree {

	private int agreeId;
	private int memberId;
	private int termsId;
	private String agreement;
}

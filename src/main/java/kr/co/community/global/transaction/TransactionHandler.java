package kr.co.community.global.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionHandler {
	
private final PlatformTransactionManager transactionManager;
	
	public TransactionStatus getStatus() {
		
		// 1. 트랜잭션 기본 설정
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		
		// 2. 격리 수준 기본 설정
		transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		
		// 3. 트랜잭션 동작 설정
		// - 트랜잭션이 이미 존재하면 참여, 없다면 새로운 트랜잭션 생성
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// 4. 트랜잭션 시작
		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		
		return status;
		
	}
	
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

}

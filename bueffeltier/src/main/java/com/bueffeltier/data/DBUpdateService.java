package com.bueffeltier.data;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.bueffeltier.logic.foundation.UserRepository;

public class DBUpdateService
{
	private static DBUpdateService instance;

	private UserRepository userRepository = UserRepository.getInstance();

	private DBUpdateService()
	{
		start();
	}

	public static DBUpdateService getInstance()
	{
		if (instance == null)
		{
			instance = new DBUpdateService();
		}
		return instance;
	}

	private Runnable getUserActivationKeyUpdateRunnableTask()
	{
		Runnable userActivationKeyUpdateRunnableTask = () -> {
//				TimeUnit.MINUTES.sleep(1);

			userRepository.deleteExpiredActivations();

		};

		return userActivationKeyUpdateRunnableTask;
	}

	private void start()
	{
		ScheduledExecutorService executorService = Executors
				.newScheduledThreadPool(1);

		executorService.scheduleWithFixedDelay(
				getUserActivationKeyUpdateRunnableTask(), 1L, 3L,
				TimeUnit.MINUTES
		); // todo: settings.
	}

//	private void start()
//	{
//		ScheduledExecutorService executorService = Executors
//				.newScheduledThreadPool(1);
//
//		executorService.scheduleWithFixedDelay(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				userRepository.deleteExpiredActivations();
//			}
//		}, 1L, 1L, TimeUnit.MINUTES); // todo: settings.
//	}
}

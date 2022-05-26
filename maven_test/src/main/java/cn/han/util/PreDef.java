package cn.han.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PreDef {

	public static Supplier<RuntimeException> err(String msg) {
		return () -> new RuntimeException(msg);
	}

	public static Function<Object, String> surround(String symbol) {
		return obj -> symbol + obj.toString() + symbol;
	}

	public static Predicate<Optional<String>> nonBlankPredicate = opt -> {
		return StringUtils.isNotBlank(opt.orElse(""));
	};

}

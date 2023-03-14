package com.thinktalentindia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shuvendu Moharana 
 * This is responsible to drive the 
 * user related queries.
 */
@RestController
public class HomeControlller {
	@GetMapping("/getName")
	public String getName(@RequestParam String fName, @RequestParam(required = false) String mName,
			@RequestParam String lName) {

		StringBuilder sb = new StringBuilder();

		// Add the first letter of the first name
		sb.append(Character.toUpperCase(fName.charAt(0))).append(" ");

		// Add the first letter of the middle name, if it exists
		if (mName != null && !mName.isEmpty()) {
			sb.append(Character.toUpperCase(mName.charAt(0))).append(" ");
		}

		// Add the last name in all caps
		sb.append(lName.toUpperCase());

		return sb.toString();
	}

}

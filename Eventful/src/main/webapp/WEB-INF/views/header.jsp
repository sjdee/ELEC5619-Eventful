<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav>
	<div class="nav-wrapper">
		<a href="/dashboard" class="brand-logo" style="font-weight:200"><i class="material-icons">all_inclusive</i>eventful</a>
		<ul class="hide-on-med-and-down right">
			<li>
				<div class="center row">
					<div class="col s12 ">
						<div class="row" id="topbarsearch">
							<div class="input-field col s6 s12">
								<i class="material-icons prefix" style="top:0">search</i> <input
									type="text" placeholder="search" id="autocomplete-input"
									class="autocomplete">
							</div>
						</div>
					</div>
				</div>
			</li>
			<li><a href="/dashboard"><i class="material-icons">dashboard</i></a></li>
			<li><a href="/createEvent"><i class="material-icons">event</i></a></li>
			<li><a href="/profile"><i class="material-icons">perm_contact_calendar</i></a></li>
			<li><a href="/logout"><i class="material-icons">subdirectory_arrow_right</i></a></li>
		</ul>
	</div>
</nav>

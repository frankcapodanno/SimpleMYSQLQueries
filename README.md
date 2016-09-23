# SimpleMYSQLQueries
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.


This software work with Java Technologies and is for teoretical use only.
more informations francesco@capodanno.click

For testing proupouse you need to install MYSQL DB and create a DB 
with this feauters (you can copy and paste this script in your MYSQL Shell):
<code>
CREATE DATABASE IF NOT EXISTS `test_connections` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `test_connections`;

CREATE TABLE `simple_data` (
  `id` int(11) NOT NULL,
  `uname` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `data` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
</code>
